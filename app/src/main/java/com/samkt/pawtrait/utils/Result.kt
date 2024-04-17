package com.samkt.pawtrait.utils

sealed class Result<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Result<T>(data = data)

    class Error(message: String?) : Result<Nothing>(message = message)
}
