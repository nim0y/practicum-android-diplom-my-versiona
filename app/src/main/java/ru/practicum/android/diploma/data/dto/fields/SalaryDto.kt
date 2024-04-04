package ru.practicum.android.diploma.data.dto.fields

import ru.practicum.android.diploma.domain.models.fields.SalaryModel

data class SalaryDto(
    val currency: String?,
    val from: Int?,
    val gross: Boolean?,
    val to: Int?
) {
    fun mapToModel(): SalaryModel {
        return SalaryModel(currency, from, gross, to)
    }
}
