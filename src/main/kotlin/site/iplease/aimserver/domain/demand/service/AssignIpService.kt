package site.iplease.aimserver.domain.demand.service

import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto

interface AssignIpService {
    fun addAssignIp(dto: AssignIpDto): Mono<AssignIpDto>
}
