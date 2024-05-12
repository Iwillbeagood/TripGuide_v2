package com.jun.tripguide_v2.feature.travelInit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.travelInit.TravelInitRoute
import com.jun.tripguide_v2.feature.travelInit.mapper.toDestinationCode

fun NavController.navigateTravelInit() {
    navigate(TravelInitRoute.route)
}

fun NavGraphBuilder.travelInitNavGraph(
    onBackClick: () -> Unit,
    onTravelInitComplete: (String, MeansType) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(
        route = TravelInitRoute.route
    ) {
        TravelInitRoute(
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTravelInitComplete = onTravelInitComplete
        )
    }
}

object TravelInitRoute {
    const val route = "travel_init"

}