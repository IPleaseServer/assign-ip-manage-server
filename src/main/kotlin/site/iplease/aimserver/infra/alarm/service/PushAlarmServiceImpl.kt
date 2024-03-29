package site.iplease.aimserver.infra.alarm.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.infra.alarm.data.message.SendAlarmMessage
import site.iplease.aimserver.infra.alarm.data.type.AlarmType
import site.iplease.aimserver.infra.message.service.MessagePublishService
import site.iplease.aimserver.infra.message.type.MessageType

@Service
class PushAlarmServiceImpl(
    private val messagePublishService: MessagePublishService
): PushAlarmService {
    override fun publish(receiverId: Long, title: String, description: String, type: AlarmType): Mono<Unit> =
        Unit.toMono()
            .map { SendAlarmMessage(
                type = type,
                receiverId = receiverId,
                title = title,
                description = description
            ) }.flatMap { messagePublishService.publish(type = MessageType.SEND_ALARM, data = it) }
}