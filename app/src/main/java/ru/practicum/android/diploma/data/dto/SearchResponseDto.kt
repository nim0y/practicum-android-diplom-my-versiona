package ru.practicum.android.diploma.data.dto

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.network.api.Response
import ru.practicum.android.diploma.domain.models.SearchResponseModel

data class SearchResponseDto(
    val items: List<VacancyDto>,
    val page: Int,
    val pages: Int,
    @SerializedName("per_page")
    val perPage: Int,
) : Response() {
    fun mapToModel() = SearchResponseModel(items.map { it.mapToModel() }, page, pages, perPage)
}
