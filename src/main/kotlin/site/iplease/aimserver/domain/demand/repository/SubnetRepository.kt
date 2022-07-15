package site.iplease.aimserver.domain.demand.repository

import reactor.core.publisher.Mono

interface SubnetRepository {
    fun existsByIp(ip: String): Mono<Boolean>
}
