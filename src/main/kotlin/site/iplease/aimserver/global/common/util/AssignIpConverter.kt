package site.iplease.aimserver.global.common.util

import reactor.core.publisher.Mono
import site.iplease.aimserver.global.common.data.dto.AssignIpDto
import site.iplease.aimserver.global.common.data.entity.AssignIp
import site.iplease.aimserver.global.accept.data.message.IpAssignDemandAcceptedMessage

interface AssignIpConverter {
    fun toDto(message: IpAssignDemandAcceptedMessage): Mono<AssignIpDto>
    fun toEntity(dto: AssignIpDto): Mono<AssignIp>
    fun toDto(entity: AssignIp): Mono<AssignIpDto>
    fun toDto(id: Long): Mono<AssignIpDto>
}
