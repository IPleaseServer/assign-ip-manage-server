package site.iplease.aimserver.global.common.service

import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.dto.AssignIpDto

interface AssignIpService {
    fun addAssignIp(dto: AssignIpDto): Mono<AssignIpDto>
    fun removeAssignIp(id: Long): Mono<Unit>
}
