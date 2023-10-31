package com.jun.tripguide_v2.feature.addtravel.startingpicker

import com.jun.tripguide_v2.core.model.Address
import com.jun.tripguide_v2.core.model.AreaCode

sealed interface StartingPickerUiEffect {

    object Idle : StartingPickerUiEffect

    data class StartingPicked(val address: Address) : StartingPickerUiEffect
}