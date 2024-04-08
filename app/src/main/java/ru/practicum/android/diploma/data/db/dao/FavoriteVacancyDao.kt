package ru.practicum.android.diploma.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.db.entity.KeySkillsEntity
import ru.practicum.android.diploma.data.db.entity.PhonesEntity
import ru.practicum.android.diploma.data.db.entity.VacancyDataEntity
import ru.practicum.android.diploma.data.db.entity.VacancyDetailsEntity

@Dao
interface FavoriteVacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhones(entity: List<PhonesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeySkills(entity: List<KeySkillsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(entity: VacancyDetailsEntity)

    @Query("DELETE FROM VacancyDetailsEntity WHERE id = :vacancyId")
    suspend fun deleteVacancy(vacancyId: String)

    @Transaction
    @Query("SELECT * FROM VacancyDetailsEntity")
    fun getVacancyList(): Flow<List<VacancyDataEntity>>
}
