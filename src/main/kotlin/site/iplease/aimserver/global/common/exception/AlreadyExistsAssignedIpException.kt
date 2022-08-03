package site.iplease.aimserver.global.common.exception

class AlreadyExistsAssignedIpException(message: String): RuntimeException("이미 존재하는 할당IP입니다! - $message")