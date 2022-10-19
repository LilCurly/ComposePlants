package com.example.soplant.domain.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val error: com.example.soplant.domain.entities.ApiError?) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: com.example.soplant.domain.entities.ApiError?): Resource<T> {
            return Resource(Status.ERROR, null, message, error)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}