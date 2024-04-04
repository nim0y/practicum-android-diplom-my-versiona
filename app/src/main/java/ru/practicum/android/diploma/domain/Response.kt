package ru.practicum.android.diploma.domain

sealed class Response {

    class Success : Response()

    class Error : Response()

}
