package site.iplease.aimserver.domain.accept.exception

import site.iplease.aimserver.global.error.IpleaseError

class AssignIpCreateFailureException(private val errorDetail: String): RuntimeException("$ERROR_MESSAGE - $errorDetail"), IpleaseError {
    companion object { private const val ERROR_MESSAGE = "할당IP 추가중, 오류가 발생하였습니다!" }

    override fun getErrorMessage() = ERROR_MESSAGE
    override fun getErrorDetail() = errorDetail
}