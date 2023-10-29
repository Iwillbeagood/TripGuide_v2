package com.jun.tripguide_v2.feature.travelroute

sealed interface TravelRouteUiEffect {

    object Idle : TravelRouteUiEffect

    object ScrollToFirstItem : TravelRouteUiEffect
}