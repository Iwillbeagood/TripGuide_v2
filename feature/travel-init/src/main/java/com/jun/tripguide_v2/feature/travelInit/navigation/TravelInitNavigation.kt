package com.jun.tripguide_v2.feature.travelInit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.core.model.Address
import com.jun.tripguide_v2.core.model.tourApi.AreaCode
import com.jun.tripguide_v2.feature.travelInit.TravelInitRoute
import com.jun.tripguide_v2.feature.travelInit.areapicker.AreaPickerRoute
import com.jun.tripguide_v2.feature.travelInit.mapper.toDestinationCode
import com.jun.tripguide_v2.feature.travelInit.mapper.toStartingPoint
import com.jun.tripguide_v2.feature.travelInit.startingpicker.StartingPickerScreen

fun NavController.navigateTravelInit() {
    navigate(TravelInitRoute.route)
}

fun NavController.navigatePickDestination() {
    navigate(TravelInitRoute.route_pick_destination)
}

fun NavController.navigatePickStartingPoint() {
    navigate(TravelInitRoute.route_pick_starting_point)
}

fun NavGraphBuilder.travelInitNavGraph(
    onBackClick: () -> Unit,
    onBackClickAreaCodes: (AreaCode, AreaCode) -> Unit,
    onBackClickAddress: (Address) -> Unit,
    onPickTravelInfoClick: () -> Unit,
    onPickStartingPointClick: () -> Unit,
    onTravelInitComplete: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(
        route = TravelInitRoute.route
    ) { navBackStackEntry ->
        val destination = navBackStackEntry.savedStateHandle.get<String>("destination")
        val startingPoint = navBackStackEntry.savedStateHandle.get<String>("startingPoint")

        TravelInitRoute(
            onBackClick = onBackClick,
            onAreaPickerClick = onPickTravelInfoClick,
            onStartingPickerClick = onPickStartingPointClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            destination = destination.toDestinationCode(),
            startingPoint = startingPoint.toStartingPoint(),
            onTravelInitComplete = onTravelInitComplete
        )
    }

    composable(
        route = TravelInitRoute.route_pick_destination
    ) {
        AreaPickerRoute(
            onBackClick = onBackClick,
            onBackClickWithData = onBackClickAreaCodes
        )
    }

    composable(
        route = TravelInitRoute.route_pick_starting_point
    ) {
        StartingPickerScreen(
            onBackClick = onBackClick,
            onBackClickAddress = onBackClickAddress
        )
    }
}

object TravelInitRoute {
    const val route = "travel_init"

    const val route_pick_destination = "route_pick_destination"

    const val route_pick_starting_point = "route_starting_point"
}