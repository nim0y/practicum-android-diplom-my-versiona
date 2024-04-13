package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.domain.models.fields.ContactsModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.EmploymentModel
import ru.practicum.android.diploma.domain.models.fields.ExperienceModel
import ru.practicum.android.diploma.domain.models.fields.KeySkillsModel
import ru.practicum.android.diploma.domain.models.fields.SalaryModel
import ru.practicum.android.diploma.domain.models.fields.ScheduleModel

data class VacancyDetailsModel(
    val id: String,
    val area: AreaModel?,
    val employer: EmployerModel?,
    val name: String?,
    val salary: SalaryModel?,
    val description: String?,
    val employment: EmploymentModel?,
    val experience: ExperienceModel?,
    val contacts: ContactsModel?,
    val schedule: ScheduleModel?,
    val keySkills: List<KeySkillsModel>?,
    val alternateUrl: String?
)
