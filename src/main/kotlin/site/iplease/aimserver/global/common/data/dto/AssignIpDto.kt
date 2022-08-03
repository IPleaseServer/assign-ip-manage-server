package site.iplease.aimserver.global.common.data.dto

data class AssignIpDto (
    val id: Long,
    val ip: String,
    val assigneeId: Long,
    val assignerId: Long
)