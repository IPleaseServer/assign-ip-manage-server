package site.iplease.aimserver.global.common.repository

import reactor.core.publisher.Mono

interface SubnetRepository {
    fun existsByIp(ip: String): Mono<Boolean>
}
