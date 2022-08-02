package site.iplease.aimserver.domain.demand.exception

class AssignIpCreateFailureException(msg: String): RuntimeException("할당IP 추가도중, 오류가 발생하였습니다! - $msg")