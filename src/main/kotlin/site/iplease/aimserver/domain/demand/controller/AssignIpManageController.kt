package site.iplease.aimserver.domain.demand.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.demand.data.response.RemoveAssignIpResponse
import site.iplease.aimserver.domain.demand.service.AssignIpService

@RequestMapping("/api/v1/assign-ip/command")
@RestController
class AssignIpManageController(
    val assignIpService: AssignIpService
) {
    @DeleteMapping("/{assignIpId}")
    fun removeAssignIp(@PathVariable assignIpId: Long): Mono<ResponseEntity<RemoveAssignIpResponse>> =
        assignIpService.removeAssignIp(assignIpId)
            .map { RemoveAssignIpResponse(assignIpId) }
            .map { ResponseEntity.ok(it) }
}