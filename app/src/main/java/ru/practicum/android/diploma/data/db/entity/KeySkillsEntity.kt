package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeySkillsEntity(
    @PrimaryKey
    val id: String,
    val vacancyId: String,
    val name: String? = null
)
