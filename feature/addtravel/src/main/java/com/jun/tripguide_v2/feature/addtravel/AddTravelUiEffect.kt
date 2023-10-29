package com.jun.tripguide_v2.feature.addtravel

sealed interface AddTravelUiEffect {

    object Idle : AddTravelUiEffect

    data class ShowDialogForTravelDuration(val visibility: Boolean): AddTravelUiEffect

    data class AddTravelComplete(val travelId: String): AddTravelUiEffect
}