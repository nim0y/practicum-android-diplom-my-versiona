package ru.practicum.android.diploma.ui.state

import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.util.ErrorVariants

sealed interface SearchScreenState {

    data object Loading : SearchScreenState

    data object LoadNextPage : SearchScreenState

    data object Default : SearchScreenState

    data class NothingFound(
        val vacancies: List<VacancyModel>,
        val found: Int
    ) : SearchScreenState

    data class Success(
        val vacancies: List<VacancyModel>,
        val found: Int
    ) : SearchScreenState

    data class Error(
        val error: ErrorVariants
    ) : SearchScreenState
}
