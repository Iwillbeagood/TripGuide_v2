package com.jun.tripguide_v2.feature.travelInit.startingpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.GetKakaoLocalByKeywordUsecase
import com.jun.tripguide_v2.core.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingPickerViewModel @Inject constructor(
    private val getKakaoLocalByKeywordUsecase: GetKakaoLocalByKeywordUsecase
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private val _uiState =
        MutableStateFlow<StartingPickerUiState>(StartingPickerUiState.Empty)
    val uiState: StateFlow<StartingPickerUiState> = _uiState

    private val _uiEffect = MutableStateFlow<StartingPickerUiEffect>(StartingPickerUiEffect.Idle)
    val uiEffect: StateFlow<StartingPickerUiEffect> = _uiEffect

    private var contentJob: Job? = null

    fun searchAddress(keyword: String) {
        if (keyword == "") return

        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _keyword.value = keyword
            _uiState.value = StartingPickerUiState.Addresses(
                addresses = getKakaoLocalByKeywordUsecase(keyword)
            )
        }
    }

    fun clearKeyword() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _keyword.value = ""
        }
    }

    fun addressPicked(address: Address) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {

            _uiEffect.value = StartingPickerUiEffect.StartingPicked(address)
        }
    }
}