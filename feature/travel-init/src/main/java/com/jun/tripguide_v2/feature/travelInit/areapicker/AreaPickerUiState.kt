package com.jun.tripguide_v2.feature.travelInit.areapicker

import com.jun.tripguide_v2.core.model.tourApi.AreaCode

sealed interface AreaPickerUiState {

    object Loading: AreaPickerUiState

    data class AreaCodes(
        val areaCodes: List<AreaCode>,
        val sigunguCodes: List<AreaCode> = emptyList()
    ) : AreaPickerUiState {

        val selectedAreaCode get() = areaCodes.find { it.isSelected }
    }
}