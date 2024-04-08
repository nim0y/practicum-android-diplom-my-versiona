package ru.practicum.android.diploma.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyDetailsEntity(
    @PrimaryKey
    val id: String,

    @Embedded("employer")
    val employer: EmployerEntity? = null,
    val name: String? = null,
    val description: String? = null,
    val alternateUrl: String? = null,
    val currencySalary: String? = null,
    val fromSalary: Int? = null,
    val grossSalary: Boolean? = null,
    val toSalary: Int? = null,
    val emailContact: String? = null,
    val nameContact: String? = null,
    val nameArea: String? = null,
    val nameEmployment: String? = null,
    val nameExperience: String? = null,
    val nameSchedule: String? = null,
)
