package com.example.exam.network

sealed class ResultHandler<out T> {
    data class Success<T>(val data: T?) : ResultHandler<T>()
    data class Error<T>(val data: T?, val message: String) : ResultHandler<T>()
    data class Loading(val loading: Boolean = false) : ResultHandler<Nothing>()
}