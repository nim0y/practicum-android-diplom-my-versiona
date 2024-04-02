package ru.practicum.android.diploma.domain.models.fields

import ru.practicum.android.diploma.data.dto.fields.AreaDto

data class AreaModel(
    val id: String?,
    val name: String?,
    val countryId: String?,
    val areas: List<AreaDto>?
)
