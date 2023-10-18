package com.jun.tripguide_v2.feature.addtravel

import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.core.model.MeansItems

sealed interface AddTravelUiEffect {

    object Idle : AddTravelUiEffect

    data class DestinationPicked(val areaCode: AreaCode): AddTravelUiEffect

    data class StartingPointPicked(val areaCode: AreaCode): AddTravelUiEffect

    data class MeansItemPicked(val meansItems: List<MeansItems>): AddTravelUiEffect
}