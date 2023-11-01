package com.jun.tripguide_v2.feature.travelInit.areapicker

import com.jun.tripguide_v2.core.model.AreaCode

sealed interface AreaPickerUiEffect {

    object Idle : AreaPickerUiEffect

    data class DestinationAreaCodePicked(val defaultAreaCode: AreaCode, val areaCode: AreaCode) : AreaPickerUiEffect
}