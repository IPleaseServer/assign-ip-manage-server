package site.iplease.aimserver.domain.query.response

import org.springframework.data.domain.Page
import site.iplease.aimserver.global.common.data.dto.AssignIpDto

data class PagableAssignIpQueryResponse(
    val data: Page<AssignIpDto>
)
