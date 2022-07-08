package site.iplease.aimserver.domain.demand.service

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.demand.data.dto.AssignIpDto
import site.iplease.aimserver.domain.demand.data.type.PolicyType
import site.iplease.aimserver.domain.demand.repository.AssignIpRepository
import site.iplease.aimserver.domain.demand.util.AssignIpConverter
import site.iplease.aimserver.domain.demand.util.AssignIpValidator

@Component
class AssignIpServiceImpl(
    private val assignIpValidator: AssignIpValidator,
    private val assignIpConverter: AssignIpConverter,
    private val assignIpRepository: AssignIpRepository
): AssignIpService {
    override fun addAssignIp(dto: AssignIpDto): Mono<AssignIpDto> =
        assignIpValidator.validate(PolicyType.ADD, dto) //할당IP 추가정책을 검사한다.
            .flatMap{ assignIpConverter.toEntity(dto) } //인자로 받은 Dto를 Entity로 변환한다.
            .flatMap { entity -> assignIpRepository.save(entity) } //변환한 Entity를 Repository에 저장한다.
            .flatMap { entity -> assignIpConverter.toDto(entity) } //저장한 Entity를 Dto로 변환하여 반환한다.
}