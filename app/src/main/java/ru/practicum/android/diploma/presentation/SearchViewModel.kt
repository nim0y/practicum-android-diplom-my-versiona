package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.Response
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.ui.state.SearchScreenState
import ru.practicum.android.diploma.util.Constants
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(private val searchInteractor: SearchInteractor) : ViewModel() {
    private val _searchState = MutableLiveData<SearchScreenState>()
    val searchState: LiveData<SearchScreenState> = _searchState

    var isClickable = true
    private var found = 0
    private var lastQuery: String? = null

    private val searchDebounce =
        debounce<String?>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { query ->
            search(query)
        }
    private val clickDebounce =
        debounce<Boolean>(Constants.SEARCH_DEBOUNCE_DELAY, viewModelScope, false) {
            isClickable = it
        }

    fun onSearchQueryChange(query: String?) {
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

    private fun search(query: String?) {
        if (query.isNullOrBlank()) return
        setState(SearchScreenState.Loading)

        viewModelScope.launch {
            searchInteractor.getVacancies(query, 1, HashMap()).collect {
                when (it) {
                    is Response.Success -> {
                        setState(SearchScreenState.Success(it.data.vacancies, it.data.found))
                        found = it.data.found
                    }

                    is Response.Error -> setState(SearchScreenState.Error(errorVariant = it.error))
                }
            }
        }
    }
}
