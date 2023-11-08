package com.jun.tripguide_v2.feature.mytravel

sealed interface MyTravelUiEffect {

    object Idle : MyTravelUiEffect

    object MyTravelComplete : MyTravelUiEffect
}