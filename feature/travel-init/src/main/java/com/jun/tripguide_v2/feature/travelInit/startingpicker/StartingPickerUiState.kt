package com.jun.tripguide_v2.feature.travelInit.startingpicker

import com.jun.tripguide_v2.core.model.Address

sealed interface StartingPickerUiState {

    object Empty: StartingPickerUiState

    data class Addresses(val addresses: List<Address> = emptyList()) : StartingPickerUiState
}