package ru.practicum.android.diploma.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.data.PrefsManager
import ru.practicum.android.diploma.domain.models.fields.AreaModel

class ChoosePlaceOfWorkViewModel(
    private val prefsManager: PrefsManager
): ViewModel() {

    fun fetchCountry(): AreaModel? {
        return prefsManager.get("Area", AreaModel::class.java)
    }
}
