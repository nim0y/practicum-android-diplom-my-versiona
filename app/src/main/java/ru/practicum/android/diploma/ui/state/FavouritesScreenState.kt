package ru.practicum.android.diploma.ui.state

import ru.practicum.android.diploma.domain.models.VacancyDetailsModel

sealed interface FavouritesScreenState {

    data object Empty : FavouritesScreenState

    data class ShowContent(val data: List<VacancyDetailsModel>) : FavouritesScreenState

    data object Error : FavouritesScreenState

}
