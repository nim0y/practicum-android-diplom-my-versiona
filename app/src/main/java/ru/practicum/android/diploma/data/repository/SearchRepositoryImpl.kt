package ru.practicum.android.diploma.data.repository

import ru.practicum.android.diploma.data.dto.SearchResponseDto
import ru.practicum.android.diploma.data.dto.VacancyDto
import ru.practicum.android.diploma.data.mapper.mapToModel
import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.data.network.client.NetworkClient
import ru.practicum.android.diploma.domain.Response
import ru.practicum.android.diploma.domain.api.SearchRepository
import ru.practicum.android.diploma.domain.models.SearchResponseModel
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.util.Constants
import ru.practicum.android.diploma.util.ErrorVariants

class SearchRepositoryImpl(private val networkClient: NetworkClient) : SearchRepository {

    override suspend fun getSimilarVacancies(id: String): Response<out SearchResponseModel> {
        val response = networkClient.doRequest(Request.SimilarVacanciesRequest(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as SearchResponseDto).mapToModel())
        } else {
            Response.Error(getErrorVariant(response.resultCode))
        }
    }

    override suspend fun getCurrentVacancyDetails(id: String): Response<out VacancyModel> {
        val response = networkClient.doRequest(Request.CurrentVacancyDetails(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as VacancyDto).mapToModel())
        } else {
            Response.Error(getErrorVariant(response.resultCode))
        }
    }

    override suspend fun getVacancies(
        query: String,
        page: Int,
        filters: HashMap<String, String>
    ): Response<out SearchResponseModel> {
        val response = networkClient.doRequest(Request.MainSearchRequest(query, page, filters))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as SearchResponseDto).mapToModel())
        } else {
            Response.Error(getErrorVariant(response.resultCode))
        }
    }

    private fun getErrorVariant(code: Int): ErrorVariants = when (code) {
        -1 -> ErrorVariants.NO_CONNECTION
        400 -> ErrorVariants.BAD_REQUEST
        404 -> ErrorVariants.NOT_FOUND
        else -> ErrorVariants.UNEXPECTED
    }
}
