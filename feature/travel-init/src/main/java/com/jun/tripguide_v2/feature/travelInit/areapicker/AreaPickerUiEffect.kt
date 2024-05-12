package com.jun.tripguide_v2.feature.travelInit.areapicker

import com.jun.tripguide_v2.core.model.DestinationCode

sealed interface AreaPickerUiEffect {

    object Idle : AreaPickerUiEffect

    data class DestinationAreaCodePicked(
        val destinationCode: DestinationCode
    ) : AreaPickerUiEffect
}