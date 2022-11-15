package com.ajdamiao.recipesapp.data.resource

sealed class Outcome<T>(val data: T? = null, val message: String? = null) {
    class Progress<T>(data: T? = null) : Outcome<T>(data)
    class Failure<T>(message: String, data: T? = null) : Outcome<T>(data, message)
    class Success<T>(data: T) : Outcome<T>(data)
}