package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel

interface FavoriteVacancyRepository {
    fun getListVacancy(): Flow<List<VacancyDetailsModel>>
    suspend fun addVacancy(vacancy: VacancyDetailsModel)
    suspend fun delVacancy(vacancyId: String)
}

