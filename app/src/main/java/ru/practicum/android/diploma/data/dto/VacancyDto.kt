package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.dto.fields.AreaDto
import ru.practicum.android.diploma.data.dto.fields.EmployerDto
import ru.practicum.android.diploma.data.dto.fields.SalaryDto

data class VacancyDto(
    val id: String,
    val area: AreaDto,
    val employer: EmployerDto?,
    val name: String?,
    val salary: SalaryDto?
)
