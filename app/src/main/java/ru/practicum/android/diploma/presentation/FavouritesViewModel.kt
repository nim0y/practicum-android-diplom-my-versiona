package ru.practicum.android.diploma.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.mapper.mapToModel
import ru.practicum.android.diploma.data.network.api.HeadHuntersApi
import ru.practicum.android.diploma.data.network.client.RetrofitInternetProvider
import ru.practicum.android.diploma.domain.interactors.FavoriteVacancyInteractor
import ru.practicum.android.diploma.ui.state.FavouritesScreenState

class FavouritesViewModel(private val interactor: FavoriteVacancyInteractor) : ViewModel() {
    val favoritesState = MutableLiveData<FavouritesScreenState>()

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = RetrofitInternetProvider.retrofit.create(HeadHuntersApi::class.java).getVacancy("95149783")
            interactor.addVacancy(result.mapToModel())

            interactor.getListVacancy().collect {
                it
            }
        }
    }

    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getListVacancy()
                .catch {
                    favoritesState.value = FavouritesScreenState.Error
                }
                .collect {
                    if (it.isEmpty()) {
                        favoritesState.value = FavouritesScreenState.Empty
                    } else {
                        favoritesState.value = FavouritesScreenState.ShowContent(it)
                    }
                }
        }
    }

}
