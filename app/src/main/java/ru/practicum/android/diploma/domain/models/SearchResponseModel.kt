package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.data.dto.VacancyDto
import ru.practicum.android.diploma.data.network.api.Response

class SearchResponseModel(
    val vacancies: List<VacancyModel>,
    val page: Int,
    val pages: Int,
    val perPage: Int,
){
}
