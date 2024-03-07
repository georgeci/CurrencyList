package com.georgeci.purevm.sample.domain.entity

sealed interface RemoteError {
    data class NetworkError(val message: String?) : RemoteError
    data class Unknown(val message: String?) : RemoteError
}