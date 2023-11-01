package com.jun.tripguide_v2.feature.travelInit.startingpicker

import com.jun.tripguide_v2.core.model.Address

sealed interface StartingPickerUiEffect {

    object Idle : StartingPickerUiEffect

    data class StartingPicked(val address: Address) : StartingPickerUiEffect
}