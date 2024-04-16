package ru.practicum.android.diploma.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.data.PrefsManager
import ru.practicum.android.diploma.domain.models.fields.AreaModel

class ChoosePlaceOfWorkViewModel(
    private val prefsManager: PrefsManager
): ViewModel() {

    private val _countryState = MutableStateFlow<AreaModel?>(null)
    val countryState = _countryState.asStateFlow()

    fun fetchCountry() {
        _countryState.value = prefsManager.get("Country", AreaModel::class.java)
    }

    fun removeCountry() {
        prefsManager.delete("Country")
        _countryState.value = null
    }
}
