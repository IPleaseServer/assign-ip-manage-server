package site.iplease.aimserver.global.common.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.entity.AssignIp

interface AssignIpRepository: ReactiveCrudRepository<AssignIp, Long> {
    fun findBy(page: Pageable): Flux<AssignIp>//https://stackoverflow.com/questions/58874827/spring-data-r2dbc-and-pagination
    fun findByAssigneeId(page: Pageable, assigneeId: Long): Flux<AssignIp>
    fun existsByIp(ip: String): Mono<Boolean>
}