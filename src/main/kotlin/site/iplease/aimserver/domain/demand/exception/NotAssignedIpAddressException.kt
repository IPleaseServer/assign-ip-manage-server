package site.iplease.aimserver.domain.demand.exception

class NotAssignedIpAddressException(message: String): RuntimeException("할당되지 않은 Ip 주소입니다! - $message")
