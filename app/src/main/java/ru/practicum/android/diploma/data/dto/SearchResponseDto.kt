package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.network.api.Response

data class SearchResponseDto(
    val itemId: Int,
    val vacancies: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    val perPage: Int,
) : Response()
