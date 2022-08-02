package site.iplease.aimserver.domain.demand.util

import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto
import site.iplease.aimserver.domain.demand.data.type.PolicyType

interface AssignIpValidator {
    fun validate(type: PolicyType, dto: AssignIpDto): Mono<Unit>
}
