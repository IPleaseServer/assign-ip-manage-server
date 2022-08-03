package site.iplease.aimserver.infra.alarm.service

import reactor.core.publisher.Mono
import site.iplease.aimserver.infra.alarm.data.type.AlarmType

interface PushAlarmService {
    fun publish( receiverId: Long, title: String, description: String, type: AlarmType = AlarmType.FCM): Mono<Unit>
}
