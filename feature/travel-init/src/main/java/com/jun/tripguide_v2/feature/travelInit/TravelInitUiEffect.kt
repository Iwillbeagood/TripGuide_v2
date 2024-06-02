package com.jun.tripguide_v2.feature.travelInit

import com.jun.tripguide_v2.core.model.MeansType

sealed interface TravelInitUiEffect {

    object Idle : TravelInitUiEffect

    object ShowTravelDurationDialog : TravelInitUiEffect

    object ShowStartingPickerDialog : TravelInitUiEffect

    object ShowDestinationPickerDialog : TravelInitUiEffect

    data class TravelInitComplete(val travelId: Long, val selectedMeans: MeansType) : TravelInitUiEffect
}