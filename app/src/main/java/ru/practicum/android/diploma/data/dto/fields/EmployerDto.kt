package ru.practicum.android.diploma.data.dto.fields

import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.LogoUrlModel

data class EmployerDto(
    val id: String?,
    val logoUrls: LogoUrlDto?,
    val name: String?,
    val trusted: Boolean?,
    val url: String?,
    val vacanciesUrl: String?
){

    fun mapToModel() = EmployerModel(id, LogoUrlModel(logoUrls?.original, logoUrls?.logo90, logoUrls?.logo240), name, trusted, url, vacanciesUrl)
}
