package ru.practicum.android.diploma.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.interactors.FavoriteVacancyInteractor
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.util.Constants
import ru.practicum.android.diploma.util.debounce

class VacancyViewModel(private val interactor: FavoriteVacancyInteractor) : ViewModel() {
    private var isClickable = true
    private val clickDebounce =
        debounce<Boolean>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, false) {
            isClickable = it
        }

    val favoriteVacanciesState = interactor.getListVacancy().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onLikeClick(vacancy: VacancyDetailsModel) {
        viewModelScope.launch {
            if (favoriteVacanciesState.value.contains(vacancy)) {
                interactor.delVacancy(vacancy.id)
            } else {
                interactor.addVacancy(vacancy)
            }
            actionOnClick()
        }
    }

    private fun actionOnClick() {
        isClickable = false
        clickDebounce(true)
    }
}
