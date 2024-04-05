package ru.practicum.android.diploma.util

enum class ErrorVariant(val code: Int) {
    NOT_FOUND(404),
    NO_CONNECTION(-1),
    UNEXPECTED(-2),
    BAD_REQUEST(400),
}
