package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.data.dto.VacancyDto

data class SearchResponse(
    val itemId: Int,
    val vacancies: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    val perPage: Int,
)
