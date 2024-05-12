package com.jun.tripguide_v2.feature.travelInit.areapicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetAreaCodeUsecase
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.tourApi.AreaCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaPickerViewModel @Inject constructor(
    private val getAreaCodeUsecase: GetAreaCodeUsecase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<AreaPickerUiState>(AreaPickerUiState.Loading)
    val uiState: StateFlow<AreaPickerUiState> get() = _uiState

    private val _uiEffect = MutableStateFlow<AreaPickerUiEffect>(AreaPickerUiEffect.Idle)
    val uiEffect: StateFlow<AreaPickerUiEffect> get() = _uiEffect

    init {
        viewModelScope.launch {
            val defaultAreaCodeFlow = flow { emit(getAreaCodeUsecase()) }

            defaultAreaCodeFlow.collect {
                _uiState.value =
                    AreaPickerUiState.AreaCodes(
                        it.toMutableList().apply { this[0] = this[0].copy(isSelected = true) }
                    )
            }
        }
    }

    private var contentJob: Job? = null

    fun upDateDefaultAreaCodeState(selectedDefaultAreaCode: AreaCode) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is AreaPickerUiState.AreaCodes) {
            return
        }

        contentJob = viewModelScope.launch {
            val areaCodes = if (selectedDefaultAreaCode.name == "전체") {
                emptyList()
            } else {
                getAreaCodeUsecase(selectedDefaultAreaCode.code)
            }

            _uiState.value = uiState.copy(
                areaCodes = uiState.areaCodes.map {
                    it.copy(
                        isSelected = it == selectedDefaultAreaCode
                    )
                },
                sigunguCodes = areaCodes
            )
        }
    }

    fun areaPicked(sigunguCode: AreaCode) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is AreaPickerUiState.AreaCodes) {
            return
        }

        contentJob = viewModelScope.launch {
            val areaCode = uiState.selectedAreaCode!!

            _uiEffect.value = AreaPickerUiEffect.DestinationAreaCodePicked(
                DestinationCode(
                    areaCode = areaCode,
                    sigunguCode = sigunguCode
                )
            )
        }
    }

    fun resetUiEffect() {
        _uiEffect.update {
            AreaPickerUiEffect.Idle
        }
    }
}