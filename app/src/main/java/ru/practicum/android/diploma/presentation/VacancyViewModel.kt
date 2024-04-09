package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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
    private var vacancyCurrent: VacancyDetailsModel? = null

    private var isClickable = true
    private val clickDebounce =
        debounce<Boolean>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, false) {
            isClickable = it
        }

    val favoriteVacanciesState = interactor.getListVacancy().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onLikeClick(vacancyId: String) {
        if (vacancyId == vacancyCurrent?.id) {
            val vacancy = vacancyCurrent
            viewModelScope.launch {
                if (favoriteVacanciesState.value.contains(vacancy)) {
                    interactor.delVacancy(vacancy!!.id)
                } else {
                    interactor.addVacancy(vacancy!!)
                }
                actionOnClick()
            }
        } else return
    }

    fun fetchDetails(id: String) {
        _uiState.value = VacancyDetailsScreenState.Loading
        viewModelScope.launch {
            val response = searchInteractor.getCurrentVacancyDetails(id)
            when (response) {
                is Response.Success -> {
                    _uiState.postValue(VacancyDetailsScreenState.Content(response.data))
                    vacancyCurrent = response.data
                }

                is Response.Error -> {
                    val favoriteVacancies = favoriteVacanciesState.value
                    val isVacancyFavorite = favoriteVacancies.any { it.id == id }
                    if (isVacancyFavorite) {
                        _uiState.postValue(VacancyDetailsScreenState.Error(response.error))
                    } else {
                        getVacancyFromDb(id)
                    }
                }
            }
        }
    }

    private fun getVacancyFromDb(id: String) {
        if (id.isEmpty()) {
            _uiState.postValue(VacancyDetailsScreenState.Error(errorVariant = ErrorVariant.NO_CONNECTION))
        }
        viewModelScope.launch {
            interactor.getVacancy(id).collect { vacancyFromDb ->
                fetchVacancyFromDb(vacancyFromDb)
            }
        }
    }

    private fun fetchVacancyFromDb(vacancyFromDb: VacancyDetailsModel) {
        vacancyCurrent = vacancyFromDb
        _uiState.postValue(VacancyDetailsScreenState.Content(vacancyCurrent as VacancyDetailsModel))
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
