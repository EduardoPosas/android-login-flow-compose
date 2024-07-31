package com.example.loginflowapp.auth.domain

interface ValidationBaseUseCase<T, R> {
    fun execute(input: T): R
}