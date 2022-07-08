package site.iplease.aimserver.domain.demand.util

import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto
import site.iplease.aimserver.global.demand.data.message.IpAssignDemandAcceptedMessage

interface AssignIpConverter {
    fun toDto(message: IpAssignDemandAcceptedMessage): Mono<AssignIpDto>
}
