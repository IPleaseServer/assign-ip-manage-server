package site.iplease.aimserver.global.common.exception

class NotAssignedIpAddressException(message: String): RuntimeException("할당되지 않은 Ip 주소입니다! - $message")
