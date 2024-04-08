package ru.practicum.android.diploma.domain.interactors

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel

interface FavoriteVacancyInteractor {
    fun getListVacancy(): Flow<List<VacancyDetailsModel>>
    suspend fun addVacancy(vacancy: VacancyDetailsModel)
    suspend fun delVacancy(vacancyId: String)
}

