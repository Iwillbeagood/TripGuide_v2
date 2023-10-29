package com.jun.tripguide_v2.feature.addtravel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.core.model.Address
import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.feature.addtravel.AddTravelRoute
import com.jun.tripguide_v2.feature.addtravel.areapicker.AreaPickerRoute
import com.jun.tripguide_v2.feature.addtravel.areapicker.mapper.toDestinationData
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
    onPickStartingPointClick: () -> Unit,
    onAddTravelComplete: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(
        route = AddTravelRoute.route
    ) { navBackStackEntry ->
        val destination = navBackStackEntry.savedStateHandle.get<String>("destination")
        val startingPoint = navBackStackEntry.savedStateHandle.get<String>("startingPoint")

        AddTravelRoute(
            onBackClick = onBackClick,
            onAreaPickerClick = onPickTravelInfoClick,
            onStartingPickerClick = onPickStartingPointClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            destination = destination?.toDestinationData(),
            startingPoint = startingPoint,
            onAddTravelComplete = onAddTravelComplete
        )
    }

    composable(
        route = AddTravelRoute.route_pick_destination
    ) {
        AreaPickerRoute(
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
}