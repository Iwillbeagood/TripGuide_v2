package com.jun.tripguide_v2.feature.addtravel.pickdestination

import com.jun.tripguide_v2.core.model.AreaCode

sealed interface PickDestinationUiEffect {

    object Idle : PickDestinationUiEffect

    data class DestinationAreaCodePicked(val destinationAreaCode: AreaCode) : PickDestinationUiEffect
}