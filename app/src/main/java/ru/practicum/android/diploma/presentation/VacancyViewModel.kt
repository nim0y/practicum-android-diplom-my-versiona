package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.Response
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.interactors.FavoriteVacancyInteractor
import ru.practicum.android.diploma.domain.models.VacancyDetailsModel
import ru.practicum.android.diploma.domain.sharing.SharingInteractor
import ru.practicum.android.diploma.ui.state.VacancyDetailsScreenState
import ru.practicum.android.diploma.util.Constants
import ru.practicum.android.diploma.util.ErrorVariant
import ru.practicum.android.diploma.util.debounce

class VacancyViewModel(
    private val interactor: FavoriteVacancyInteractor,
    private val searchInteractor: SearchInteractor,
    private val sharingInteractor: SharingInteractor
) : ViewModel() {

    private val _uiState = MutableLiveData<VacancyDetailsScreenState>()
    val uiState: LiveData<VacancyDetailsScreenState> = _uiState
    val favoriteVacanciesState = MutableLiveData<Boolean>()
    private var vacancyCurrent: VacancyDetailsModel? = null
    private var isClickable = true
    private val clickDebounce =
        debounce<Boolean>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, false) {
            isClickable = it
        }

    fun onLikeClick() {
        vacancyCurrent ?: return
        viewModelScope.launch(Dispatchers.IO) {
            if (favoriteVacanciesState.value == true) {
                interactor.delVacancy(vacancyCurrent!!.id)
            } else {
                interactor.addVacancy(vacancyCurrent!!)
            }
            favoriteVacanciesState.postValue(favoriteVacanciesState.value?.not())
            actionOnClick()
        }
    }

    fun fetchDetails(id: String) {
        _uiState.value = VacancyDetailsScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = searchInteractor.getCurrentVacancyDetails(id)) {
                is Response.Success -> {
                    _uiState.postValue(VacancyDetailsScreenState.Content(response.data))
                    vacancyCurrent = response.data
                    interactor.getVacancy(response.data.id)
                        .catch { favoriteVacanciesState.postValue(false) }
                        .collect {
                            favoriteVacanciesState.postValue(true)
                        }
                }

                is Response.Error -> {
                    getVacancyFromDb(id, response.error)
                }
            }
        }
    }

    private fun getVacancyFromDb(id: String, error: ErrorVariant?) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getVacancy(id)
                .catch { exception ->
                    favoriteVacanciesState.postValue(false)
                    _uiState.postValue(VacancyDetailsScreenState.Error(error ?: ErrorVariant.NO_CONNECTION))
                }
                .collect { vacancyFromDb ->
                    vacancyCurrent = vacancyFromDb
                    favoriteVacanciesState.postValue(true)
                    _uiState.postValue(VacancyDetailsScreenState.Content(vacancyFromDb))
                }
        }
    }

    private fun actionOnClick() {
        isClickable = false
        clickDebounce(true)
    }

    fun vacancyShare(url: String) {
        sharingInteractor.shareVacancy(url)
    }

    fun sendEmail(email: String, subject: String) {
        sharingInteractor.sendEmail(email, subject)
    }

    fun dialPhone(phoneNumber: String) {
        sharingInteractor.dialPhoneNumber(phoneNumber)
    }
}
