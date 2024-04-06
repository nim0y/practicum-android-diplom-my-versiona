package ru.practicum.android.diploma.data.sharing

interface ExternalNavigator {
    fun sendEmail(email: String, subject: String)
    fun dialPhoneNumber(phoneNumber: String)
    fun shareVacancy(url: String)
}
