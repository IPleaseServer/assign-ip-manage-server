package site.iplease.aimserver.domain.query.controller

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.aimserver.domain.query.config.DataQueryProperty
import site.iplease.aimserver.domain.query.response.AssignIpQueryResponse
import site.iplease.aimserver.domain.query.response.ExistsAssignIpQueryResponse
import site.iplease.aimserver.domain.query.response.PagableAssignIpQueryResponse
import site.iplease.aimserver.domain.query.service.AssignIpQueryService

@RestController
@RequestMapping("/api/v1/assign-ip/query")
class AssignIpQueryController(
    private val dataQueryProperty: DataQueryProperty,
    private val assignIpQueryService: AssignIpQueryService
) {
    @GetMapping("/all")
    fun getAllAssignIp(@RequestParam page: Int): Mono<ResponseEntity<PagableAssignIpQueryResponse>> =
        PageRequest.of(page, dataQueryProperty.all.pageSize).toMono()
            .flatMap { assignIpQueryService.getAllAssignIp(it) }
            .map { PagableAssignIpQueryResponse(it) }
            .map { ResponseEntity.ok(it) }

    @GetMapping("/assignee")
    fun getAllAssignIpByAssigneeId(@RequestParam page: Int, @RequestParam assigneeId: Long): Mono<ResponseEntity<PagableAssignIpQueryResponse>> =
        PageRequest.of(page, dataQueryProperty.byAssignee.pageSize).toMono()
            .flatMap { assignIpQueryService.getAllAssignIpByAssigneeId(it, assigneeId) }
            .map { PagableAssignIpQueryResponse(it) }
            .map { ResponseEntity.ok(it) }

    @GetMapping("/{assignIpId}")
    fun getAssignIpById(@PathVariable assignIpId: Long): Mono<ResponseEntity<AssignIpQueryResponse>> =
        assignIpQueryService.getAssignIpById(assignIpId)
            .map { AssignIpQueryResponse(it) }
            .map { ResponseEntity.ok(it) }

    @GetMapping("/{assignIpId}/exists")
    fun existsAssignIpById(@PathVariable assignIpId: Long): Mono<ResponseEntity<ExistsAssignIpQueryResponse>> =
        assignIpQueryService.existsAssignIpById(assignIpId)
            .map { ExistsAssignIpQueryResponse(it) }
            .map { ResponseEntity.ok(it) }
}