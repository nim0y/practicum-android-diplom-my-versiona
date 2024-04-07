package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.SearchResponseDto
import ru.practicum.android.diploma.data.dto.VacancyDetailsDto
import ru.practicum.android.diploma.data.dto.VacancyDto
import ru.practicum.android.diploma.data.dto.fields.AreaDto
import ru.practicum.android.diploma.data.dto.fields.ContactsDto
import ru.practicum.android.diploma.data.dto.fields.EmployerDto
import ru.practicum.android.diploma.data.dto.fields.EmploymentDto
import ru.practicum.android.diploma.data.dto.fields.ExperienceDto
import ru.practicum.android.diploma.data.dto.fields.KeySkillsDto
import ru.practicum.android.diploma.data.dto.fields.PhonesDto
import ru.practicum.android.diploma.data.dto.fields.SalaryDto
import ru.practicum.android.diploma.data.dto.fields.ScheduleDto
import ru.practicum.android.diploma.domain.models.SearchResponseModel
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.domain.models.fields.ContactsModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.EmploymentModel
import ru.practicum.android.diploma.domain.models.fields.ExperienceModel
import ru.practicum.android.diploma.domain.models.fields.KeySkillsModel
import ru.practicum.android.diploma.domain.models.fields.LogoUrlModel
import ru.practicum.android.diploma.domain.models.fields.PhonesModel
import ru.practicum.android.diploma.domain.models.fields.SalaryModel
import ru.practicum.android.diploma.domain.models.fields.ScheduleModel

fun VacancyDto.mapToModel() = VacancyModel(id, area.mapToModel(), employer?.mapToModel(), name, salary?.mapToModel())

fun SearchResponseDto.mapToModel() = SearchResponseModel(found, items.map { it.mapToModel() }, page, pages, perPage)

fun VacancyDetailsDto.mapToModel() = VacancyDetailsModel(
    id,
    area?.mapToModel(),
    employer?.mapToModel(),
    name,
    salary?.mapToModel(),
    description,
    employment?.mapToModel(),
    experience?.mapToModel(),
    contacts?.mapToModel(),
    schedule?.mapToModel(),
    keySkills?.map { it.mapToModel() },
    alternateUrl
)

fun SalaryDto.mapToModel() = SalaryModel(currency, from, gross, to)

fun EmployerDto.mapToModel() = EmployerModel(
    id,
    LogoUrlModel(logoUrls?.original, logoUrls?.logo90, logoUrls?.logo240),
    name,
    trusted,
    url,
    vacanciesUrl
)

fun AreaDto.mapToModel(): AreaModel {
    val areas = areas?.map { it.mapToModel() }
    return AreaModel(id, name, countryId, areas)
}

fun EmploymentDto.mapToModel() = EmploymentModel(id, name)

fun ExperienceDto.mapToModel() = ExperienceModel(id, name)

fun ScheduleDto.mapToModel() = ScheduleModel(id, name)
fun PhonesDto.mapToModel() = PhonesModel(cityCode, comment, countryCode, formatted, number)

fun KeySkillsDto.mapToModel() = KeySkillsModel(name)
fun ContactsDto.mapToModel(): ContactsModel {
    val phones = phones?.map { it.mapToModel() }
    return ContactsModel(email, name, phones)
}
