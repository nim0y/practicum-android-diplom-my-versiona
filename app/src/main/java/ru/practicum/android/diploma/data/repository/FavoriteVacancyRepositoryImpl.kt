package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.db.AppDataBase
import ru.practicum.android.diploma.data.mapper.mapToModel
import ru.practicum.android.diploma.domain.mapToEntity
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel

class FavoriteVacancyRepositoryImpl(
    private val db: AppDataBase
) : FavoriteVacancyRepository {

    override fun getListVacancy(): Flow<List<VacancyDetailsModel>> =
        db.favoriteVacancyDao().getVacancyList().map { list ->
            list.map { it.mapToModel() }
        }

    override suspend fun addVacancy(vacancy: VacancyDetailsModel) {
        val entity = vacancy.mapToEntity()
        db.favoriteVacancyDao().insertVacancy(entity.vacancyDetails)
        db.favoriteVacancyDao().insertKeySkills(entity.keySkills ?: listOf())
        db.favoriteVacancyDao().insertPhones(entity.phonesContact ?: listOf())
    }

    override suspend fun delVacancy(vacancyId: String) {
        db.favoriteVacancyDao().deleteVacancy(vacancyId)
    }
}
