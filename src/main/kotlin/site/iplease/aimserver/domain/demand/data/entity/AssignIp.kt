package site.iplease.aimserver.domain.demand.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class AssignIp(
    @Id val id: Long,
    val ip: String,
    val assignerId: Long,
    val assigneeId: Long
)