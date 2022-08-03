package site.iplease.aimserver.global.common.exception

class WrongSubnetAddressException(message: String): RuntimeException("잘못된 서브넷 주소입니다! = $message")
