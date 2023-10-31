package com.jun.tripguide_v2.feature.addtravel

sealed interface TravelInitUiEffect {

    object Idle : TravelInitUiEffect

    data class ShowDialogForTravelDuration(val visibility: Boolean): TravelInitUiEffect

    data class TravelInitComplete(val travelId: String): TravelInitUiEffect
}