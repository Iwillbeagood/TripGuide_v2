package com.jun.tripguide_v2.feature.mytravelPlan

sealed interface MyTravelPlanUiEffect {

    object Idle : MyTravelPlanUiEffect

    object ShowEditConfirmationDialog : MyTravelPlanUiEffect

    object ShowRoutesMap : MyTravelPlanUiEffect
}