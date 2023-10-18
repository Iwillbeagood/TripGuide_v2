package com.jun.tripguide_v2.feature.addtravel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.core.model.Address
import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.feature.addtravel.AddTravelRoute
import com.jun.tripguide_v2.feature.addtravel.areapicker.AreaPickerScreen
import com.jun.tripguide_v2.feature.addtravel.startingpicker.StartingPickerScreen

fun NavController.navigateAddTravel() {
    navigate(AddTravelRoute.route)
}

fun NavController.navigatePickDestination() {
    navigate(AddTravelRoute.route_pick_destination)
}

fun NavController.navigatePickStartingPoint() {
    navigate(AddTravelRoute.route_pick_starting_point)
}

fun NavGraphBuilder.addTravelNavGraph(
    onBackClick: () -> Unit,
    onBackClickAreaCodes: (AreaCode, AreaCode) -> Unit,
    onBackClickAddress: (Address) -> Unit,
    onPickTravelInfoClick: () -> Unit,
    onPickStartingPointClick: () -> Unit
) {
    composable(
        route = AddTravelRoute.route
    ) { navBackStackEntry ->
        val destination = navBackStackEntry.savedStateHandle.get<String>("destination")
        val startingPoint = navBackStackEntry.savedStateHandle.get<String>("startingPoint")

        AddTravelRoute(
            onBackClick = onBackClick,
            onPickDestinationClick = onPickTravelInfoClick,
            onPickStartingPointClick = onPickStartingPointClick,
            destination = destination,
            startingPoint = startingPoint,
        )
    }

    composable(
        route = AddTravelRoute.route_pick_destination
    ) {
        AreaPickerScreen(
            onBackClick = onBackClick,
            onBackClickWithData = onBackClickAreaCodes
        )
    }

    composable(
        route = AddTravelRoute.route_pick_starting_point
    ) {
        StartingPickerScreen(
            onBackClick = onBackClick,
            onBackClickAddress = onBackClickAddress
        )
    }
}

object AddTravelRoute {
    const val route = "add_travel"

    const val route_pick_destination = "route_pick_destination"

    const val route_pick_starting_point = "route_starting_point"

    fun detailRoute(travelId: Int): String = "$route/$travelId"
}