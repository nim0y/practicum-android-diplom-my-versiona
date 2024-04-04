package ru.practicum.android.diploma.domain

sealed class Response<T: Any> {

    class Success<T: Any>(val data: T) : Response<T>()

    class Error : Response<Nothing>()

}
