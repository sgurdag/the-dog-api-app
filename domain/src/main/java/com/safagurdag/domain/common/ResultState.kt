package com.safagurdag.domain.common

sealed class ResultState<out T> {
    data class Loading<out T>(val value: T? = null) : ResultState<T>()

    data class Success<out T>(val value: T) : ResultState<T>()

    data class Failure<out T>(val failureReason: FailureReason) : ResultState<T>()

    companion object {

        fun <T> loading(value: T?): ResultState<T> = Loading(value)

        fun <T> success(value: T): ResultState<T> = Success(value)

        fun <T> failure(failureReason: FailureReason): ResultState<T> = Failure(failureReason)

    }
}

