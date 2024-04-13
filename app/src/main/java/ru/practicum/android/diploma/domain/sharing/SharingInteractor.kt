package ru.practicum.android.diploma.domain.sharing

interface SharingInteractor {
    fun sendEmail(email: String, subject: String)
    fun dialPhoneNumber(phone: String)
    fun shareVacancy(url: String)
}
