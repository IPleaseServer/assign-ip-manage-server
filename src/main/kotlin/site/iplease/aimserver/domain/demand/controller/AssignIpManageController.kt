package site.iplease.aimserver.domain.demand.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.iplease.aimserver.domain.demand.data.response.RemoveAssignIpResponse

@RequestMapping("/api/v1/assign-ip")
@RestController
class AssignIpManageController {
    @DeleteMapping("/{assignIpId}")
    fun removeAssignIp(@PathVariable assignIpId: Long): ResponseEntity<RemoveAssignIpResponse> =
            ResponseEntity.ok(RemoveAssignIpResponse(assignIpId))
}