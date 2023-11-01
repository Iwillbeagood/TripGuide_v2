package com.jun.tripguide_v2.feature.travelSearch

sealed interface TravelSearchUiEffect {

    object Idle : TravelSearchUiEffect

    object ScrollToFirstItem : TravelSearchUiEffect

    object TravelSearchComplete : TravelSearchUiEffect
}