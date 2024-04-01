package ru.practicum.android.diploma.data.network.client

import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.data.network.api.Response

interface NetworkClient {
    suspend fun doRequest(request: Request): Response
}
