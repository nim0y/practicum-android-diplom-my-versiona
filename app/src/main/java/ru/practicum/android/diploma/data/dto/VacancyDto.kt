package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.dto.customFields.AreaDto
import ru.practicum.android.diploma.data.dto.customFields.EmployerDto
import ru.practicum.android.diploma.data.dto.customFields.SalaryDto

data class VacancyDto(
    val id :String,
    val area:AreaDto,
    val employer:EmployerDto?,
    val name:String?,
    val salary: SalaryDto?
)
