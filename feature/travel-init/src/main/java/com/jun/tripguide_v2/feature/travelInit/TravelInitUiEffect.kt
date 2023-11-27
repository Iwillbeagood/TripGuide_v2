package com.jun.tripguide_v2.feature.travelInit

sealed interface TravelInitUiEffect {

    object Idle : TravelInitUiEffect

    object ShowTravelDurationDialog : TravelInitUiEffect

    data class TravelInitComplete(val travelId: String) : TravelInitUiEffect
}