package ru.practicum.android.diploma.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.data.PrefsManager
import ru.practicum.android.diploma.data.dto.fields.AreaDto
import ru.practicum.android.diploma.data.mapper.mapToModel
import ru.practicum.android.diploma.data.network.api.Request
import ru.practicum.android.diploma.data.network.api.Response
import ru.practicum.android.diploma.data.network.api.ResponseList
import ru.practicum.android.diploma.data.network.client.NetworkClient
import ru.practicum.android.diploma.domain.models.fields.AreaModel
import ru.practicum.android.diploma.ui.state.ChooseCountryState
import java.net.ResponseCache

class ChooseCountryViewModel(
    private val networkClient: NetworkClient,
    private val prefsManager: PrefsManager
) : ViewModel() {

    private val _state = MutableStateFlow<ChooseCountryState>(ChooseCountryState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val response = networkClient.doRequest(Request.LoadAreas(null))
            if (response.resultCode != 200) _state.value = ChooseCountryState.Error()
            if (response is ResponseList<*>) {
                val result = ChooseCountryState.Success((response as ResponseList<List<AreaDto>>).data?.map { it.mapToModel() } ?: emptyList())
                _state.value = result
            }
        }
    }

    fun onItemClick(item: AreaModel) {
        prefsManager.save("Area", item)
    }
}
