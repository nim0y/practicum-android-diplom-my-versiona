package ru.practicum.android.diploma.data.dto.fields

import ru.practicum.android.diploma.domain.models.fields.AreaModel

data class AreaDto(
    val id: String?,
    val name: String?,
    val countryId: String?,
    val areas: List<AreaDto>?
) {
    fun mapToModel(): AreaModel {
        val areas = areas?.map { it.mapToModel() }
        return AreaModel(id, name, countryId, areas)
    }
}
