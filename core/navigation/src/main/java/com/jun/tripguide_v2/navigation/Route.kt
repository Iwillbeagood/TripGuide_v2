package com.jun.tripguide_v2.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object TravelInit : Route

    @Serializable
    data class MyTravelPlanRoute(val travelId: String) : Route

    @Serializable
    data class TouristDetail(val touristId: String) : Route

    @Serializable
    data class TouristAdd(val travelId: String) : Route

    @Serializable
    data class CarInformation(val travelId: String) : Route

    @Serializable
    data class TrainInformation(val travelId: String) : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object MyTravel : MainTabRoute

    @Serializable
    data object RecommendTravel : MainTabRoute


    @Serializable
    data object Setting : MainTabRoute
}