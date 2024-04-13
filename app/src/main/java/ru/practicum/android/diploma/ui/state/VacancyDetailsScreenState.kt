package ru.practicum.android.diploma.ui.state

import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.util.ErrorVariant

sealed interface VacancyDetailsScreenState {
    data object Loading : VacancyDetailsScreenState

    data class Content(val vacancyDetails: VacancyDetailsModel) : VacancyDetailsScreenState

    data class Error(val errorVariant: ErrorVariant) : VacancyDetailsScreenState

}
