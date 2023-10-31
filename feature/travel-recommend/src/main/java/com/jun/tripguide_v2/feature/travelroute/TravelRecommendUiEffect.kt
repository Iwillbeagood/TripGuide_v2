package com.jun.tripguide_v2.feature.travelroute

sealed interface TravelRecommendUiEffect {

    object Idle : TravelRecommendUiEffect

    object ScrollToFirstItem : TravelRecommendUiEffect

    object TravelRecommendComplete : TravelRecommendUiEffect
}