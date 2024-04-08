package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.repository.FavoriteVacancyRepository
import ru.practicum.android.diploma.domain.interactors.FavoriteVacancyInteractor
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel

class FavoriteVacancyImpl(
    private val repository: FavoriteVacancyRepository
) : FavoriteVacancyInteractor {

    override fun getListVacancy(): Flow<List<VacancyDetailsModel>> = repository.getListVacancy()

    override suspend fun addVacancy(vacancy: VacancyDetailsModel) = repository.addVacancy(vacancy)

    override suspend fun delVacancy(vacancyId: String) = repository.delVacancy(vacancyId)
}
