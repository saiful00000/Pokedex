package com.shaiful.pokedex.util

sealed class ResData <T> (val data: T? = null, val message: String? = null){
    class Success<T>(data: T) : ResData<T>(data)
    class Error<T>(data: T? = null, message: String) : ResData<T>(data, message)
    class Loading<T>(data: T? = null) : ResData<T>(data)
}