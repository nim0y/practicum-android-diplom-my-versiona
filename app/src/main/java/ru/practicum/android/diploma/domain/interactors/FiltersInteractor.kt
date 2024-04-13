package ru.practicum.android.diploma.domain.interactors

import ru.practicum.android.diploma.domain.Response
import ru.practicum.android.diploma.domain.models.filters.Area
import ru.practicum.android.diploma.domain.models.filters.FiltersSettings
import ru.practicum.android.diploma.domain.models.filters.Industry

interface FiltersInteractor {
    fun getPrefs(): FiltersSettings
    fun savePrefs(settings: FiltersSettings)
    fun clearPrefs()

    suspend fun getArea(id: String?): Response<out List<Area>>

    suspend fun getIndustry(): Response<out List<Industry>>
}