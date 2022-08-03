package site.iplease.aimserver.domain.query.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("iplease.query")
data class DataQueryProperty(
    val all: QueryProperty,
    val byAssignee: QueryProperty,
)

data class QueryProperty(
    val pageSize: Int
)