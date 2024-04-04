package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.domain.Response

interface MainRepository {

    suspend fun getSimilarVacancies(id: String): Response

    suspend fun getCurrentVacancyDetails(id: String): Response

    suspend fun getVacancies(
        query: String,
        page: Int,
        filters: HashMap<String, String>
    ): Response
}
