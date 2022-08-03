package site.iplease.aimserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import site.iplease.aimserver.domain.query.config.DataQueryProperty

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = [DataQueryProperty::class])
class AssignIpManageServerApplication

fun main(args: Array<String>) {
	runApplication<AssignIpManageServerApplication>(*args)
}
