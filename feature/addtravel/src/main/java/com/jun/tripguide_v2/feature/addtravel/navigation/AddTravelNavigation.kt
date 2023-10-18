package com.jun.tripguide_v2.feature.addtravel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.feature.addtravel.AddTravelRoute
import com.jun.tripguide_v2.feature.addtravel.pickdestination.PickDestinationScreen

fun NavController.navigateAddTravel() {
    navigate(AddTravelRoute.route)
}

fun NavController.navigatePickDestination() {
    navigate(AddTravelRoute.route_pick_destination)
}

fun NavGraphBuilder.addTravelNavGraph(
    onBackClick: () -> Unit,
    onBackClickWithData: (AreaCode) -> Unit,
    onPickTravelInfoClick: () -> Unit,
    onPickStartingPointClick: () -> Unit
) {
    composable(
        route = AddTravelRoute.route
    ) { navBackStackEntry ->
        val destination = navBackStackEntry.savedStateHandle.get<String>("")

        AddTravelRoute(
            onBackClick = onBackClick,
            onPickDestinationClick = onPickTravelInfoClick,
            onPickStartingPointClick = onPickStartingPointClick,
            destination = destination
        )
    }

    composable(
        route = AddTravelRoute.route_pick_destination,
        arguments = listOf(
            navArgument("travelId") {
                type = NavType.StringType
            }
        )
    ) {
        PickDestinationScreen(
            onBackClick = onBackClick,
            onBackClickWithData = onBackClickWithData
        )
    }
}

object AddTravelRoute {
    const val route = "add_travel"

    const val route_pick_destination = "route_pick_destination"

    fun detailRoute(travelId: Int): String = "$route/$travelId"
}