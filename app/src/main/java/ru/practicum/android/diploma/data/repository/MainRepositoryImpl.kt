package ru.practicum.android.diploma.data.repository

import ru.practicum.android.diploma.data.dto.SearchResponseDto
import ru.practicum.android.diploma.data.dto.VacancyDto
import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.data.network.client.NetworkClient
import ru.practicum.android.diploma.domain.Response
import ru.practicum.android.diploma.domain.api.MainRepository
import ru.practicum.android.diploma.domain.models.SearchResponseModel
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.util.Constants

class MainRepositoryImpl(private val networkClient: NetworkClient) : MainRepository {

    override suspend fun getSimilarVacancies(id: String): Response<out SearchResponseModel> {
        val response = networkClient.doRequest(Request.SimilarVacanciesRequest(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as SearchResponseDto).mapToModel())
        } else {
            Response.Error()
        }
    }

    override suspend fun getCurrentVacancyDetails(id: String): Response<out VacancyModel> {
        val response = networkClient.doRequest(Request.CurrentVacancyDetails(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) {
            Response.Success((response as VacancyDto).mapToModel())
        } else {
            Response.Error()
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
            Response.Error()
        }
    }
}
