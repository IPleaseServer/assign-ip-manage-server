package site.iplease.aimserver.domain.demand.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto
import site.iplease.aimserver.domain.demand.data.entity.AssignIp
import site.iplease.aimserver.global.demand.data.message.IpAssignDemandAcceptedMessage

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

    override fun toEntity(dto: AssignIpDto): Mono<AssignIp> =
        Unit.toMono().map { AssignIp(
            id = dto.id,
            ip = dto.ip,
            assignerId = dto.assignerId,
            assigneeId = dto.assigneeId
        ) }
}