package com.jun.tripguide_v2.feature.addtravel.areapicker

import com.jun.tripguide_v2.core.model.AreaCode

sealed interface AreaPickerUiState {

    object Loading: AreaPickerUiState

    data class AreaCodes(val defaultAreaCodes: List<AreaCode>, val areaCodes: List<AreaCode>? = null) : AreaPickerUiState
}