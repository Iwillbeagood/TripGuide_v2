package com.jun.tripguide_v2.feature.travelSearch.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.feature.travelSearch.TravelSearchRoute

fun NavController.navigateTravelSearchRoute(travelId: String) {
    navigate(TravelSearchRoute.detailRoute(travelId))
}

fun NavGraphBuilder.travelSearchNavGraph(
    onBackClick: () -> Unit,
    onTravelSearchComplete: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(
        route = TravelSearchRoute.detailRoute("{travelId}"),
        arguments = listOf(
            navArgument("travelId") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("travelId")

        TravelSearchRoute(
            travelId = travelId ?: "",
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTravelSearchComplete = onTravelSearchComplete
        )
    }
}

object TravelSearchRoute {
    private const val route = "travel_search"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}