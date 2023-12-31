package com.jun.tripguide_v2.feature.travelInit.areapicker

import com.jun.tripguide_v2.core.model.tourApi.AreaCode

sealed interface AreaPickerUiState {

    object Loading: AreaPickerUiState

    data class AreaCodes(val defaultAreaCodes: List<AreaCode>, val areaCodes: List<AreaCode> = emptyList()) : AreaPickerUiState
}