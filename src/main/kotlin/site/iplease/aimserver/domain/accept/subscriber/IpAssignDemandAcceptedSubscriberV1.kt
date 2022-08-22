package site.iplease.aimserver.domain.accept.subscriber

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.accept.exception.AssignIpCreateFailureException
import site.iplease.aimserver.global.common.service.AssignIpService
import site.iplease.aimserver.global.common.util.AssignIpConverter
import site.iplease.aimserver.global.common.data.message.AssignIpCreateMessage
import site.iplease.aimserver.global.accept.data.message.IpAssignDemandAcceptedErrorOnManageMessage
import site.iplease.aimserver.global.accept.data.message.IpAssignDemandAcceptedMessage
import site.iplease.aimserver.global.accept.subscriber.IpAssignDemandAcceptedSubscriber
import site.iplease.aimserver.infra.alarm.data.type.AlarmType
import site.iplease.aimserver.infra.alarm.service.PushAlarmService
import site.iplease.aimserver.infra.message.service.MessagePublishService
import site.iplease.aimserver.infra.message.type.MessageType

@Component
class IpAssignDemandAcceptedSubscriberV1(
    private val assignIpConverter: AssignIpConverter,
    private val assignIpService: AssignIpService,
    private val pushAlarmService: PushAlarmService,
    private val messagePublishService: MessagePublishService
): IpAssignDemandAcceptedSubscriber {
    override fun subscribe(message: IpAssignDemandAcceptedMessage) {
        //메세지를 AssignIpDto로 Convert한다.
        assignIpConverter.toDto(message)
            //AssignIpDto로 할당IP를 추가한다.
            .flatMap { dto -> assignIpService.addAssignIp(dto) }
            .onErrorResume{ Mono.error(AssignIpCreateFailureException(it.localizedMessage)) } //비즈니스 로직 내 오류 발생에 대한 중단점
            //푸시알림서비스로 관리자에게 신청수락 성공함 FCM알림을 보낸다.
            .flatMap { dto -> pushAlarmService.publish(dto.assignerId, "신청 수락에 성공했어요", "${dto.ip}를 할당해서 신청을 수락하셧어요").map { dto } }
            //푸시알림서비스로 신청자에게 신청 수락됨 FCM알림을 보낸다.
            .flatMap { dto -> pushAlarmService.publish(dto.assignerId, "신청이 수락됬어요", "${dto.ip}를 할당받았어요! 자세한 내용은 메일을 확인해주세요").map { dto } }
            //푸시알림서비스로 신청자에게 할당IP 부여됨 MAIL알림을 보낸다.
            .flatMap { dto ->
                pushAlarmService.publish(dto.assignerId, "새로운 IP를 할당받았어요!", """
                축하해요! 새로운 IP를 할당받았어요!
                
                > 수락된 신청 제목: ${message.title}
                > 
                > 할당받은 IP: ${message.assignIp}
                
                혹 기존에 다른 IP를 할당받으셧다면, 해당 IP 또한 당연히 같이 사용하실 수 있어요.
                
                해당 IP는 ${message.expireAt}에 만료될 예정이에요. 혹, 이전에 IP사용이 종료된다면, IP할당 해제신청을 부탁드릴게요!
            """.trimIndent(), AlarmType.EMAIL).map { dto } }
            //추가된 IP의 만료기한을 설정하기위한 메세지를 발행한다.
            .flatMap { messagePublishService.publish(MessageType.ASSIGN_IP_CREATE, AssignIpCreateMessage(it.id, message.expireAt)) }
            //오류 발생시, 메세지를 발행한다.
            .onErrorResume(AssignIpCreateFailureException::class.java) { throwable ->
                val errorMessage = IpAssignDemandAcceptedErrorOnManageMessage( //TODO 나중에 Converter 인터페이스 만들고 위임할 것
                    message.demandId,
                    message.originStatus,
                    message.issuerId,
                    message.demandIssuerId,
                    message.title,
                    message.description,
                    message.usage,
                    message.expireAt,
                    message.assignIp,
                    throwable.localizedMessage)
                messagePublishService.publish(MessageType.IP_ASSIGN_DEMAND_ACCEPTED_ERROR_ON_MANAGE, errorMessage)
            }.block()
    }
}