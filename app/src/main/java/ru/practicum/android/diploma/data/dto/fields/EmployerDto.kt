package ru.practicum.android.diploma.data.dto.fields

import com.google.gson.annotations.SerializedName

data class EmployerDto(
    val id: String?,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrlDto?,
    val name: String?,
    val trusted: Boolean?,
    val url: String?,
    @SerializedName("vacancies_url")
    val vacanciesUrl: String?
)
