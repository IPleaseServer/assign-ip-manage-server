package site.iplease.aimserver.global.demand.data.message

import java.time.LocalDate

data class AssignIpCreateMessage(val id: Long, val expireAt: LocalDate)