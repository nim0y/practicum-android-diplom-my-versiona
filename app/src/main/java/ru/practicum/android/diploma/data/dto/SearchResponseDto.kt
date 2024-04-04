package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.network.api.Response
import ru.practicum.android.diploma.domain.models.SearchResponseModel

data class SearchResponseDto(
    val itemId: Int,
    val vacancies: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    val perPage: Int,
) : Response() {
    fun mapToModel() = SearchResponseModel(itemId, vacancies.map { it.mapToModel() }, page, pages, perPage)
}
