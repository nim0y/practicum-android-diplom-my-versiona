package ru.practicum.android.diploma.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.db.entity.FavoriteVacancyEntity

@Dao
interface FavoriteVacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(entity: FavoriteVacancyEntity)

    @Query("DELETE FROM favorite_vacancies WHERE id = :vacancyId")
    suspend fun deleteVacancy(vacancyId: String)

    @Query("SELECT * FROM favorite_vacancies")
    fun getVacancyList(): Flow<List<FavoriteVacancyEntity>>
}
