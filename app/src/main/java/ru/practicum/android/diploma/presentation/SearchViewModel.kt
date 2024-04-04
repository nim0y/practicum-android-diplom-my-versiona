package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.practicum.android.diploma.domain.api.MainRepository
import ru.practicum.android.diploma.ui.state.SearchScreenState
import ru.practicum.android.diploma.util.Constants
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var isClickable = true
    private val _searchState = MutableLiveData<SearchScreenState>()
    val searchState: LiveData<SearchScreenState> = _searchState

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

    fun search(query: String?) {
        if (query.isNullOrBlank()) return
        setState(SearchScreenState.Loading)
    }
}
