package site.iplease.aimserver.domain.demand.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import site.iplease.aimserver.domain.demand.data.entity.AssignIp

interface AssignIpRepository: ReactiveCrudRepository<AssignIp, Long> {
    fun existsByIp(ip: String): Mono<Boolean>
}