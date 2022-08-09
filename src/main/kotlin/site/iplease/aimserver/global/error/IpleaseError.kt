package site.iplease.aimserver.global.error

interface IpleaseError {
    fun getErrorMessage(): String
    fun getErrorDetail(): String
}