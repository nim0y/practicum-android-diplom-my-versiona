package ru.practicum.android.diploma.domain.sharing.impl

import ru.practicum.android.diploma.data.sharing.ExternalNavigator
import ru.practicum.android.diploma.domain.models.fields.Phone
import ru.practicum.android.diploma.domain.sharing.SharingInteractor
import ru.practicum.android.diploma.util.PhoneFormatter

class SharingInteractorImpl(private val externalNavigator: ExternalNavigator) : SharingInteractor {
    override fun sendEmail(email: String, subject: String) {
        externalNavigator.sendEmail(email, subject)
    }

    override fun dialPhoneNumber(phone: Phone) {
        val phoneNumberString = PhoneFormatter.formatPhone(phone.country, phone.city, phone.number)
        externalNavigator.dialPhoneNumber(phoneNumberString)
    }

    override fun shareVacancy(url: String) {
        externalNavigator.shareVacancy(url)
    }
}
