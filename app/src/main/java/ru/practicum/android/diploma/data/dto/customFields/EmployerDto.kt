package ru.practicum.android.diploma.data.dto.customFields

data class EmployerDto(
    val id: String?,
    val logoUrls: LogoUrlDto?,
    val name: String?,
    val trusted: Boolean?,
    val url: String?,
    val vacanciesUrl: String?
)
