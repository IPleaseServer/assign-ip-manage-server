package site.iplease.aimserver.domain.demand.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import site.iplease.aimserver.domain.demand.data.entity.AssignIp

interface AssignIpRepository: ReactiveCrudRepository<AssignIp, Long>