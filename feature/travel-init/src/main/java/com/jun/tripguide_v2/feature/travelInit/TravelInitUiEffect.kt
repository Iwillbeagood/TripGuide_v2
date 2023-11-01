package com.jun.tripguide_v2.feature.travelInit

sealed interface TravelInitUiEffect {

    object Idle : TravelInitUiEffect

    data class ShowDialogForTravelDuration(val visibility: Boolean): TravelInitUiEffect

    data class TravelInitComplete(val travelId: String): TravelInitUiEffect
}