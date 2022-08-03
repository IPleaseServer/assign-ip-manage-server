package site.iplease.aimserver.global.common.util

import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.dto.AssignIpDto
import site.iplease.aimserver.global.common.data.type.PolicyType

interface AssignIpValidator {
    fun validate(type: PolicyType, dto: AssignIpDto): Mono<Unit>
}
