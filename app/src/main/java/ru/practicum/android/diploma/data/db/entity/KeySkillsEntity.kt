package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = VacancyDetailsEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("vacancyId")
    )]
)
data class KeySkillsEntity(
    @PrimaryKey
    val id: String,
    val vacancyId: String,
    val name: String? = null
)
