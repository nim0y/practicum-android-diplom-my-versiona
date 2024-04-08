package ru.practicum.android.diploma.data.db.entity

data class EmployerEntity(
    val id: String? = null,
    val name: String? = null,
    val trusted: Boolean? = null,
    val url: String? = null,
    val vacanciesUrl: String? = null,
    val original: String? = null,
    val logo90: String? = null,
    val logo240: String? = null
)
