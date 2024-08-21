package com.example.loginflowapp.auth.util

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: String, val data: T?) : Resource<T>()
    data class Loading<T>(val isLoading: Boolean) : Resource<T>()
}