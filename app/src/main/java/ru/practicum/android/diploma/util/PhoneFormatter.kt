package ru.practicum.android.diploma.util

import java.util.Locale

object PhoneFormatter {

    private const val PHONE_NUMBER_DIGITS_DROP_FIRST_3 = 3
    private const val PHONE_NUMBER_DIGITS_DROP_FIRST_5 = 5
    private const val PHONE_NUMBER_DIGITS_DROP_LAST_2 = 2
    private const val PHONE_NUMBER_DIGITS_DROP_LAST_4 = 4

    fun formatPhone(country: String, city: String, number: String?): String {
        return String.format(
            Locale.ENGLISH,
            "+%s (%s) %s-%s-%s",
            country,
            city,
            number?.dropLast(PHONE_NUMBER_DIGITS_DROP_LAST_4),
            number?.drop(PHONE_NUMBER_DIGITS_DROP_FIRST_3)?.dropLast(PHONE_NUMBER_DIGITS_DROP_LAST_2),
            number?.drop(PHONE_NUMBER_DIGITS_DROP_FIRST_5)
        )
    }
}
