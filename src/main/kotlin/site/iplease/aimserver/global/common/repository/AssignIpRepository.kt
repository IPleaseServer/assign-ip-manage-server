package site.iplease.aimserver.global.common.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.entity.AssignIp

interface AssignIpRepository: ReactiveCrudRepository<AssignIp, Long> {
    fun existsByIp(ip: String): Mono<Boolean>
}