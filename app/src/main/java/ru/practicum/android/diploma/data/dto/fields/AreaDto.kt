package ru.practicum.android.diploma.data.dto.fields

data class AreaDto(
    val id: String?,
    val name: String?,
    val countryId: String?,
    val areas: List<AreaDto>?
)
