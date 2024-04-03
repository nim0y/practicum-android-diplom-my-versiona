package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.SalaryModel

data class VacancyModel(
    val id: String,
    val area: AreaModel?,
    val employer: EmployerModel?,
    val name: String?,
    val salary: SalaryModel?
)
