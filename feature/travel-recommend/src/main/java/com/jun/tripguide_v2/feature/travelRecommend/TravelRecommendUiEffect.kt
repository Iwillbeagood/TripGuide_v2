package com.jun.tripguide_v2.feature.travelRecommend

sealed interface TravelRecommendUiEffect {

    object Idle : TravelRecommendUiEffect

    object ScrollToFirstItem : TravelRecommendUiEffect

    object TravelRecommendComplete : TravelRecommendUiEffect
}