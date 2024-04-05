package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.SearchResponseDto
import ru.practicum.android.diploma.data.dto.VacancyDto
import ru.practicum.android.diploma.data.dto.fields.AreaDto
import ru.practicum.android.diploma.data.dto.fields.EmployerDto
import ru.practicum.android.diploma.data.dto.fields.SalaryDto
import ru.practicum.android.diploma.domain.models.SearchResponseModel
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.domain.models.fields.EmployerModel
import ru.practicum.android.diploma.domain.models.fields.LogoUrlModel
import ru.practicum.android.diploma.domain.models.fields.SalaryModel

fun VacancyDto.mapToModel() = VacancyModel(id, area.mapToModel(), employer?.mapToModel(), name, salary?.mapToModel())

fun SearchResponseDto.mapToModel() = SearchResponseModel(items.map { it.mapToModel() }, page, pages, perPage)

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
