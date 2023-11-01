package com.jun.tripguide_v2.feature.travelSearch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.travelSearch.TravelSearchRoute

fun NavController.navigateTravelSearchRoute() {
    navigate(TravelSearchRoute.route)
}

fun NavGraphBuilder.travelSearchNavGraph(
    onBackClick: () -> Unit,
    onTravelSearchComplete: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(
        route = TravelSearchRoute.route
    ) {
        TravelSearchRoute(
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTravelSearchComplete = onTravelSearchComplete
        )
    }
}

object TravelSearchRoute {
    const val route = "travel_search"
}