package ru.practicum.android.diploma.domain.models

class SearchResponseModel(
    val found: Int,
    val vacancies: List<VacancyModel>,
    val page: Int,
    val pages: Int,
    val perPage: Int,
)
