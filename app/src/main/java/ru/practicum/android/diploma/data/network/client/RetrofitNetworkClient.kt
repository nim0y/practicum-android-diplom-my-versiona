package ru.practicum.android.diploma.data.network.client

import android.content.Context
import ru.practicum.android.diploma.data.network.api.HeadHuntersApi
import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.data.network.api.Request.MainSearchRequest
import ru.practicum.android.diploma.data.network.api.Response
import ru.practicum.android.diploma.util.Constants.CODE_SUCCESS

class RetrofitNetworkClient(
    private val context: Context,
    private val headHuntersApi: HeadHuntersApi
) : NetworkClient {

    override suspend fun doRequest(request: Request): Response {
        var response = Response()
        return try {
            response = when (request) {
                is MainSearchRequest -> {
                    headHuntersApi.getVacancies(
                        query = request.query,
                        page = request.page,
                        filters = request.filters
                    )
                }

                is Request.SimilarVacanciesRequest -> {
                    headHuntersApi.getSimilarVacancies(request.id)
                }

                is Request.CurrentVacancyDetails -> {
                    headHuntersApi.getVacancy(request.id)
                }
            }
            response.apply { resultCode = CODE_SUCCESS }
        } catch (exception: java.net.UnknownHostException) {
            return Response().apply { resultCode = -1 }
        }
    }
}
