package site.iplease.aimserver.global.common.exception

import site.iplease.aimserver.global.error.IpleaseError

class AlreadyExistsAssignedIpException(private val errorDetail: String): RuntimeException("$ERROR_MESSAGE - $errorDetail"), IpleaseError {
    companion object { private const val ERROR_MESSAGE = "이미 존재하는 할당IP입니다!" }

    override fun getErrorMessage() = ERROR_MESSAGE
    override fun getErrorDetail() = errorDetail
}