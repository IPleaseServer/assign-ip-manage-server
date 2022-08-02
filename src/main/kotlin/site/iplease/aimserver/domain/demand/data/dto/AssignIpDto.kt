package site.iplease.aimserver.domain.demand.data.dto

data class AssignIpDto (
    val id: Long,
    val ip: String,
    val assigneeId: Long,
    val assignerId: Long
)