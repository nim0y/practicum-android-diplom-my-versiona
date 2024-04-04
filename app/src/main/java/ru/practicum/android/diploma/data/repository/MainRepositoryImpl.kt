package ru.practicum.android.diploma.data.repository

import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.data.network.client.NetworkClient
import ru.practicum.android.diploma.domain.Response
import ru.practicum.android.diploma.domain.api.MainRepository
import ru.practicum.android.diploma.util.Constants

class MainRepositoryImpl(private val networkClient: NetworkClient) : MainRepository {

    override suspend fun getSimilarVacancies(id: String): Response {

        val response = networkClient.doRequest(Request.SimilarVacanciesRequest(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) Response.Success()
        else Response.Error()

    }

    override suspend fun getCurrentVacancyDetails(id: String): Response {
        val response = networkClient.doRequest(Request.CurrentVacancyDetails(id))
        return if (response.resultCode == Constants.CODE_SUCCESS) Response.Success()
        else Response.Error()
    }

    override suspend fun getVacancies(query: String, page: Int, filters: HashMap<String, String>): Response {
        val response = networkClient.doRequest(Request.MainSearchRequest(query,page,filters))
        return if (response.resultCode == Constants.CODE_SUCCESS) Response.Success()
        else Response.Error()
    }
}
