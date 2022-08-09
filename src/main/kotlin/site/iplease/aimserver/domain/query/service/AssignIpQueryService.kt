package site.iplease.aimserver.domain.query.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.dto.AssignIpDto

interface AssignIpQueryService {
    fun getAllAssignIp(page: PageRequest): Mono<Page<AssignIpDto>>
    fun getAllAssignIpByAssigneeId(page: PageRequest, assigneeId: Long): Mono<Page<AssignIpDto>>
    fun getAssignIpById(assignIpId: Long): Mono<AssignIpDto>
    fun existsAssignIpById(assignIpId: Long): Mono<Boolean>
}
