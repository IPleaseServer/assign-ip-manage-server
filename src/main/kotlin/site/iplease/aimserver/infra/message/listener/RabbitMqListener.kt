package site.iplease.aimserver.infra.message.listener

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.global.accept.data.message.IpAssignDemandAcceptedMessage
import site.iplease.aimserver.global.accept.subscriber.IpAssignDemandAcceptedSubscriber
import site.iplease.aimserver.infra.message.type.MessageType

@Component
class RabbitMqListener(
    private val ipAssignDemandAcceptedSubscriber: IpAssignDemandAcceptedSubscriber,
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["iplease.ip.assign.manage"])
    fun listen(message: Message) {
        val routingKey = message.messageProperties.receivedRoutingKey
        val payload = String(message.body)
        handleMessage(MessageType.of(routingKey), payload)
            .doOnError{ throwable ->
                logger.error("메세지를 직렬화하는도중 오류가 발생하였습니다!")
                logger.error("exception: ${throwable.localizedMessage}")
                logger.error("payload(string): ${String(message.body)}")
                logger.error("payload(byte): ${message.body}")
            }.onErrorResume { Mono.empty() }
            .block()
    }

    private fun handleMessage(type: MessageType, payload: String): Mono<Unit> =
        when(type) {
            MessageType.IP_ASSIGN_DEMAND_ACCEPTED -> objectMapper.toMono()
                .map { it.readValue(payload, IpAssignDemandAcceptedMessage::class.java) }
                .map { message -> ipAssignDemandAcceptedSubscriber.subscribe(message) }
            else -> {
                logger.warn("처리대상이 아닌 메세지가 바인딩되어있습니다!")
                logger.warn("routingKey: ${type.routingKey}")
                logger.warn("payload(string): $payload")
                Unit.toMono()
            }
        }
}