package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
import ru.practicum.android.diploma.util.Constants.BAD_REQUEST_ERROR
import ru.practicum.android.diploma.util.Constants.NOT_FOUND_ERROR
import ru.practicum.android.diploma.util.Constants.NO_CONNECTION_ERROR
import ru.practicum.android.diploma.util.ErrorVariant

class SearchRepositoryImpl(private val networkClient: NetworkClient) : SearchRepository {

    override suspend fun getSimilarVacancies(id: String): Response<out SearchResponseModel> {
        val response = networkClient.doRequest(Request.SimilarVacanciesRequest(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as SearchResponseDto).mapToModel())
        } else {
            Response.Error(getErrorType(response.resultCode))
        }
    }

    override suspend fun getCurrentVacancyDetails(id: String): Response<out VacancyModel> {
        val response = networkClient.doRequest(Request.CurrentVacancyDetails(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as VacancyDto).mapToModel())
        } else {
            Response.Error(getErrorType(response.resultCode))
        }
    }

    override suspend fun getVacancies(
        query: String,
        page: Int,
        filters: HashMap<String, String>
    ): Flow<Response<out SearchResponseModel>> = flow {
        val response = networkClient.doRequest(Request.MainSearchRequest(query, page, filters))
        if (response.resultCode == Constants.CODE_SUCCESS) {
            emit(Response.Success((response as SearchResponseDto).mapToModel()))
        } else {
            emit(Response.Error(getErrorType(response.resultCode)))
        }
    }

    private fun getErrorType(code: Int): ErrorVariant = when (code) {
        NO_CONNECTION_ERROR -> ErrorVariant.NO_CONNECTION
        BAD_REQUEST_ERROR -> ErrorVariant.BAD_REQUEST
        NOT_FOUND_ERROR -> ErrorVariant.NOT_FOUND
        else -> ErrorVariant.UNEXPECTED
    }
}
