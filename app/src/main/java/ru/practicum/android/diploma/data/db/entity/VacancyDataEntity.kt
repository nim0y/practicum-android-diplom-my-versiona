package ru.practicum.android.diploma.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class VacancyDataEntity(
    @Embedded val vacancyDetails: VacancyDetailsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "vacancyId"
    )
    val phonesContact: List<PhonesEntity>? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "vacancyId"
    )
    val keySkills: List<KeySkillsEntity>? = null,
)
