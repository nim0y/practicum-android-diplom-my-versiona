package ru.practicum.android.diploma.data.dto.fields

import com.google.gson.annotations.SerializedName

data class AreaDto(
    val id: String?,
    val name: String?,
    @SerializedName("country_id")
    val countryId: String?,
    val areas: List<AreaDto>?
)
