package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.models.VacancyModel
import ru.practicum.android.diploma.ui.State.SearchScreenState
import ru.practicum.android.diploma.util.Constants
import ru.practicum.android.diploma.util.debounce

class SearchViewModel() : ViewModel() {

    var isClickable = true
    private val _searchState = MutableLiveData<SearchScreenState>()
    val searchState: LiveData<SearchScreenState> = _searchState

    private var lastQuery: String? = null
    val vacanciesList: MutableList<VacancyModel> = mutableListOf()

    private val searchDebounce =
        debounce<String?>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { query ->
            search(query)
        }
    private val clickDebounce =
        debounce<Boolean>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, false) {
            isClickable = it
        }

    fun onSearchQueryChange(query: String?) {
        if (query.isNullOrBlank() || query != lastQuery)
            vacanciesList.clear()

        lastQuery = query
        searchDebounce(query)
    }

    private fun setState(state: SearchScreenState) {
        _searchState.postValue(state)
    }

    fun actionOnClick() {
        isClickable = false
        clickDebounce(true)
    }

    fun search(query: String?) {
        if (query.isNullOrBlank()) return
        setState(SearchScreenState.Loading)
        viewModelScope.launch {
            // TODO:
        }
    }
}


