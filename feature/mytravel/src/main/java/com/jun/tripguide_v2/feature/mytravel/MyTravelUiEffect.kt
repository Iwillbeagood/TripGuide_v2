package com.jun.tripguide_v2.feature.mytravel

import com.jun.tripguide_v2.core.model.Travel

sealed interface MyTravelUiEffect {

    object Idle : MyTravelUiEffect

    data class ShowDeleteDialog(val travel: Travel) : MyTravelUiEffect
}