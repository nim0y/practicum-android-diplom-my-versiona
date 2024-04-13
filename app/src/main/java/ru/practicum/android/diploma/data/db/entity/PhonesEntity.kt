package ru.practicum.android.diploma.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhonesEntity(
    @PrimaryKey
    val id: String,
    val vacancyId: String,

    val number: String?,
    val cityCode: String? = null,
    val comment: String? = null,
    val countryCode: String? = null,
    val formatted: String? = null,

)
