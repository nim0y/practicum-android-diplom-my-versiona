package ru.practicum.android.diploma.domain.models.fields

data class ContactsModel(
    val email: String?,
    val name: String?,
    val phones: List<PhonesModel>?
)
