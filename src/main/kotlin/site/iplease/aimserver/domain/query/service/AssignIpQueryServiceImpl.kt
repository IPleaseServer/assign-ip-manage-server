package site.iplease.aimserver.domain.query.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.dto.AssignIpDto
import site.iplease.aimserver.global.common.data.entity.AssignIp
import site.iplease.aimserver.global.common.repository.AssignIpRepository
import site.iplease.aimserver.global.common.util.AssignIpConverter

@Service
class AssignIpQueryServiceImpl(
    val assignIpRepository: AssignIpRepository,
    val assignIpConverter: AssignIpConverter
): AssignIpQueryService {
    override fun getAllAssignIp(page: PageRequest): Mono<Page<AssignIpDto>> = assignIpRepository.findBy(page).convert()
    override fun getAllAssignIpByAssigneeId(page: PageRequest, assigneeId: Long): Mono<Page<AssignIpDto>> = assignIpRepository.findByAssigneeId(page, assigneeId).convert()
    override fun getAssignIpById(assignIpId: Long): Mono<AssignIpDto> = assignIpRepository.findById(assignIpId).flatMap { assignIpConverter.toDto(it) }
    override fun existsAssignIpById(assignIpId: Long): Mono<Boolean> = assignIpRepository.existsById(assignIpId)

    private fun Flux<AssignIp>.convert(): Mono<Page<AssignIpDto>> =
        flatMap { assignIpConverter.toDto(it) }
            .collectList()
            .map { PageImpl(it) }
}
