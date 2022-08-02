package site.iplease.aimserver.domain.demand.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto
import site.iplease.aimserver.domain.demand.data.type.PolicyType
import site.iplease.aimserver.domain.demand.exception.AlreadyAssignedIpAddressException
import site.iplease.aimserver.domain.demand.exception.NotAssignedIpAddressException
import site.iplease.aimserver.domain.demand.exception.WrongSubnetAddressException
import site.iplease.aimserver.domain.demand.repository.AssignIpRepository
import site.iplease.aimserver.domain.demand.repository.SubnetRepository

@Component
class AssignIpValidatorImpl(
    private val assignIpRepository: AssignIpRepository,
    private val subnetRepository: SubnetRepository
): AssignIpValidator {
    override fun validate(type: PolicyType, dto: AssignIpDto): Mono<Unit> =
        when(type) {
            PolicyType.ADD -> isIpExists(dto.ip, false).flatMap { isSubnetExistsByIp(dto.ip) }
            PolicyType.REMOVE -> isIpExists(dto.ip)
        }

    private fun isSubnetExistsByIp(ip: String, isNeedExists: Boolean = true): Mono<Unit> =
        subnetRepository.existsByIp(ip)
            .flatMap { isExists ->
                if(isExists == isNeedExists) Unit.toMono()
                else if(isNeedExists) Mono.error(WrongSubnetAddressException("${ip}는 학교에서 소유한 서브넷 풀에 존재하는 Ip가 아닙니다."))
                else Mono.error(WrongSubnetAddressException("${ip}는 학교에서 소유한 서브넷풀에 존재하는 Ip입니다."))
            }

    private fun isIpExists(ip: String, isNeedExists: Boolean = true): Mono<Unit> =
        assignIpRepository.existsByIp(ip)
            .flatMap { isExists ->
                if(isExists == isNeedExists) Unit.toMono()
                else if(isNeedExists) Mono.error(NotAssignedIpAddressException("${ip}는 할당되지 않은Ip주소입니다."))
                else Mono.error(AlreadyAssignedIpAddressException("${ip}는 이미 할당되었습니다!"))
            }
}