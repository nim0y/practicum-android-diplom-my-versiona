package ru.practicum.android.diploma.data.dto

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.fields.AreaDto
import ru.practicum.android.diploma.data.dto.fields.ContactsDto
import ru.practicum.android.diploma.data.dto.fields.EmployerDto
import ru.practicum.android.diploma.data.dto.fields.EmploymentDto
import ru.practicum.android.diploma.data.dto.fields.ExperienceDto
import ru.practicum.android.diploma.data.dto.fields.KeySkillsDto
import ru.practicum.android.diploma.data.dto.fields.SalaryDto
import ru.practicum.android.diploma.data.dto.fields.ScheduleDto
import ru.practicum.android.diploma.data.network.api.Response

data class VacancyDetailsDto(
    val id: String,
    val area: AreaDto?,
    val employer: EmployerDto?,
    val name: String?,
    val salary: SalaryDto?,
    val description: String?,
    val employment: EmploymentDto?,
    val experience: ExperienceDto?,
    val contacts: ContactsDto?,
    val schedule: ScheduleDto?,
    @SerializedName("key_skills")
    val keySkills: List<KeySkillsDto>?,
    @SerializedName("alternate_url")
    val alternateUrl: String?
) : Response()

