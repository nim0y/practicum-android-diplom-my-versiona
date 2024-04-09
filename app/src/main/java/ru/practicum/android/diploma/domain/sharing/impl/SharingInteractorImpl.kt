package ru.practicum.android.diploma.domain.sharing.impl

import ru.practicum.android.diploma.data.sharing.ExternalNavigator
import ru.practicum.android.diploma.domain.sharing.SharingInteractor

class SharingInteractorImpl(private val externalNavigator: ExternalNavigator) : SharingInteractor {
    override fun sendEmail(email: String, subject: String) {
        externalNavigator.sendEmail(email, subject)
    }

    override fun dialPhoneNumber(phone: String) {
        externalNavigator.dialPhoneNumber(phone)
    }

    override fun shareVacancy(url: String) {
        externalNavigator.shareVacancy(url)
    }
}
