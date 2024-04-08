package ru.practicum.android.diploma.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.data.db.dao.FavoriteVacancyDao
import ru.practicum.android.diploma.data.db.entity.KeySkillsEntity
import ru.practicum.android.diploma.data.db.entity.PhonesEntity
import ru.practicum.android.diploma.data.db.entity.VacancyDetailsEntity

@Database(
    version = 1,
    entities = [
        VacancyDetailsEntity::class,
        PhonesEntity::class,
        KeySkillsEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "appDataBase.db"
    }

    abstract fun favoriteVacancyDao(): FavoriteVacancyDao
}
