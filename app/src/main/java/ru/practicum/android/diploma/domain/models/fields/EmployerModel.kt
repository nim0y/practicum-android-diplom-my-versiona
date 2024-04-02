package ru.practicum.android.diploma.domain.models.fields

import ru.practicum.android.diploma.data.dto.fields.LogoUrlDto

data class EmployerModel(
    val id: String?,
    val logoUrls: LogoUrlDto?,
    val name: String?,
    val trusted: Boolean?,
    val url: String?,
    val vacanciesUrl: String?
)
