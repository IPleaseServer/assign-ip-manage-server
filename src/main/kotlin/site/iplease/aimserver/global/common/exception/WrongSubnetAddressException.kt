package site.iplease.aimserver.global.common.exception

import site.iplease.aimserver.global.error.IpleaseError

class WrongSubnetAddressException(private val errorDetail: String): RuntimeException("$ERROR_MESSAGE - $errorDetail"), IpleaseError {
    companion object { private const val ERROR_MESSAGE = "잘못된 서브넷 주소입니다!" }

    override fun getErrorMessage() = ERROR_MESSAGE
    override fun getErrorDetail() = errorDetail
}
