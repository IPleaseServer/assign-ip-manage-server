package site.iplease.aimserver.global.common.exception

import site.iplease.aimserver.global.error.IpleaseError

class UnknownAssignIpException(private val errorDetail: String): RuntimeException("$ERROR_MESSAGE - $errorDetail"), IpleaseError {
    companion object { private const val ERROR_MESSAGE = "존재하지 않는 할당IP입니다!" }

    override fun getErrorMessage() = ERROR_MESSAGE
    override fun getErrorDetail() = errorDetail
}