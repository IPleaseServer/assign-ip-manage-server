package site.iplease.aimserver.domain.demand.repository

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class DummySubnetRepository: SubnetRepository {
    override fun existsByIp(ip: String): Mono<Boolean> =
        extractSubnet(ip)
            .map { subnet -> listOf(
                "10.120.71",
                "10.120.72",
                "10.120.73",
                "10.120.74",
                "10.120.75"
            ).contains(subnet) }

    private fun extractSubnet(ip: String): Mono<String> =
        ip.split(".").toMono()
            .map { "${it[0]}.${it[1]}.${it[2]}" }
            .onErrorContinue { throwable, _ -> Mono.error<Unit>(RuntimeException("ip를 통해 subnet을 추출하던 중, 오류가 발생하였습니다.", throwable)) }
}