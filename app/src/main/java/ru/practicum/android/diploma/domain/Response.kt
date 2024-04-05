package ru.practicum.android.diploma.domain

import ru.practicum.android.diploma.util.ErrorVariants

sealed class Response<T : Any> {
    class Success<T : Any>(val data: T) : Response<T>()
    class Error(error: ErrorVariants) : Response<Nothing>()
}
