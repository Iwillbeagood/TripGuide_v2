package com.jun.tripguide_v2.feature.addtravel.pickdestination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.GetAreaCodeUsecase
import com.jun.tripguide_v2.core.domain.usecase.GetDefaultAreaCodeUsecase
import com.jun.tripguide_v2.core.model.AreaCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickDestinationViewModel @Inject constructor(
    private val getDefaultAreaCodeUsecase: GetDefaultAreaCodeUsecase,
    private val getAreaCodeUsecase: GetAreaCodeUsecase
) : ViewModel() {

    private val _pickDestinationUiState =
        MutableStateFlow<PickDestinationUiState>(PickDestinationUiState.Loading)
    val pickDestinationUiState: StateFlow<PickDestinationUiState> = _pickDestinationUiState

    private val _pickDestinationUiEffect = MutableStateFlow<PickDestinationUiEffect>(PickDestinationUiEffect.Idle)
    val pickDestinationUiEffect: StateFlow<PickDestinationUiEffect> = _pickDestinationUiEffect

    init {
        viewModelScope.launch {
            val defaultAreaCodeFlow = flow { emit(getDefaultAreaCodeUsecase()) }

            defaultAreaCodeFlow.collect {
                _pickDestinationUiState.value =
                    PickDestinationUiState.AreaCodes(
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

        val uiState = pickDestinationUiState.value

        if (uiState !is PickDestinationUiState.AreaCodes) {
            return
        }

        contentJob = viewModelScope.launch {
            val areaCodes = if (selectedDefaultAreaCode.name == "전체") {
                null
            } else {
                getAreaCodeUsecase(selectedDefaultAreaCode.code)
            }

            _pickDestinationUiState.value = uiState.copy(
                defaultAreaCodes = uiState.defaultAreaCodes.map {
                    it.copy(
                        isSelected = it == selectedDefaultAreaCode
                    )
                },
                areaCodes = areaCodes
            )
        }
    }

    fun pickDestinationAreaCode(areaCode: AreaCode) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = pickDestinationUiState.value

        if (uiState !is PickDestinationUiState.AreaCodes) {
            return
        }

        contentJob = viewModelScope.launch {

            val defaultAreaCode = uiState.defaultAreaCodes.find { it.isSelected }!!

            _pickDestinationUiEffect.value = PickDestinationUiEffect.DestinationAreaCodePicked(defaultAreaCode, areaCode)
        }
    }
}