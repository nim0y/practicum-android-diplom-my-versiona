package ru.practicum.android.diploma.util

import ru.practicum.android.diploma.util.Constants.BAD_REQUEST_ERROR
import ru.practicum.android.diploma.util.Constants.NOT_FOUND_ERROR
import ru.practicum.android.diploma.util.Constants.NO_CONNECTION_ERROR
import ru.practicum.android.diploma.util.Constants.UNEXPECTED_ERROR

enum class ErrorVariant(val code: Int) {
    NOT_FOUND(NOT_FOUND_ERROR),
    NO_CONNECTION(NO_CONNECTION_ERROR),
    UNEXPECTED(UNEXPECTED_ERROR),
    BAD_REQUEST(BAD_REQUEST_ERROR),
}
