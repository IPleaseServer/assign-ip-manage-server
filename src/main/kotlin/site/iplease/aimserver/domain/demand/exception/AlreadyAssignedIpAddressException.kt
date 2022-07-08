package site.iplease.aimserver.domain.demand.exception

class AlreadyAssignedIpAddressException(message: String): RuntimeException("이미 할당된 IP주소입니다! - $message")