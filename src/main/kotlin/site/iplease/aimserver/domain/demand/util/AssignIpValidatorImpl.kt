package site.iplease.aimserver.domain.demand.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto
import site.iplease.aimserver.domain.demand.data.type.PolicyType
import site.iplease.aimserver.domain.demand.exception.AlreadyAssignedIpAddressException
import site.iplease.aimserver.domain.demand.repository.AssignIpRepository

@Component
class AssignIpValidatorImpl(
    private val assignIpRepository: AssignIpRepository
): AssignIpValidator {
    override fun validate(type: PolicyType, dto: AssignIpDto): Mono<Unit> =
        when(type) {
            PolicyType.ADD -> isIpExists(dto.ip, false)
        }

    private fun isIpExists(ip: String, isNeedExists: Boolean = true): Mono<Unit> =
        assignIpRepository.existsByIp(ip)
            .flatMap { isExists ->
                if(isExists == isNeedExists) Unit.toMono()
                else Mono.error(AlreadyAssignedIpAddressException("$ip 는 이미 할당되었습니다!"))
            }
}