package site.iplease.aimserver.domain.demand.exception

class AlreadyExistsAssignedIpException(message: String): RuntimeException("이미 존재하는 할당IP입니다! - $message")