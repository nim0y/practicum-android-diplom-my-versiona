package ru.practicum.android.diploma.domain.sharing

import ru.practicum.android.diploma.domain.models.fields.Phone

interface SharingInteractor {
    fun sendEmail(email: String, subject: String)
    fun dialPhoneNumber(phone: Phone)
    fun shareVacancy(url: String)
}
