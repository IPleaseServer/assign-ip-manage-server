package site.iplease.aimserver.domain.common.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.global.common.data.dto.AssignIpDto
import site.iplease.aimserver.global.common.data.entity.AssignIp
import site.iplease.aimserver.global.common.util.AssignIpConverter
import site.iplease.aimserver.global.accept.data.message.IpAssignDemandAcceptedMessage

@Component
class AssignIpConverterImpl: AssignIpConverter {
    override fun toDto(message: IpAssignDemandAcceptedMessage): Mono<AssignIpDto> =
        Unit.toMono().map { AssignIpDto(
            id = 0,
            ip = message.assignIp,
            assignerId = message.issuerId,
            assigneeId = message.demandIssuerId
        ) }

    override fun toDto(entity: AssignIp): Mono<AssignIpDto> =
        Unit.toMono().map { AssignIpDto(
            id = entity.id,
            ip = entity.ip,
            assignerId = entity.assignerId,
            assigneeId = entity.assigneeId
        ) }

    override fun toDto(id: Long): Mono<AssignIpDto> =
        Unit.toMono().map { AssignIpDto(
            id = id,
            ip = "",
            assignerId = -1,
            assigneeId = -1
        ) }

    override fun toEntity(dto: AssignIpDto): Mono<AssignIp> =
        Unit.toMono().map { AssignIp(
            id = dto.id,
            ip = dto.ip,
            assignerId = dto.assignerId,
            assigneeId = dto.assigneeId
        ) }
}