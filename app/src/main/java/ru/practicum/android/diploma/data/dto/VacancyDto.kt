package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.dto.fields.AreaDto
import ru.practicum.android.diploma.data.dto.fields.EmployerDto
import ru.practicum.android.diploma.data.dto.fields.SalaryDto
import ru.practicum.android.diploma.data.network.api.Response
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel

data class VacancyDto(
    val id: String,
    val area: AreaDto,
    val employer: EmployerDto?,
    val name: String?,
    val salary: SalaryDto?
): Response() {
    fun mapToModel() = VacancyModel(id, area.mapToModel(), employer?.mapToModel(), name, salary?.mapToModel()  )
}
