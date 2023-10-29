package com.jun.tripguide_v2.feature.travelroute.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.feature.travelroute.TravelRoute

fun NavController.navigateTravelRoute(travelId: String) {
    navigate(TravelRouteRoute.detailRoute(travelId))
}

fun NavGraphBuilder.travelRouteNavGraph(
    onBackClick: () -> Unit
) {
    composable(
        route = TravelRouteRoute.detailRoute("{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("id")
        TravelRoute(
            travelId = travelId ?: "",
            onBackClick = onBackClick
        )
    }
}

object TravelRouteRoute {
    const val route = "travel_route"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}