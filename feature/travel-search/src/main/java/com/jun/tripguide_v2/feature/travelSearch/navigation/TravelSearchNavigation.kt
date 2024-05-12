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
    onTouristDetail: (String) -> Unit,
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
        val string = navBackStackEntry.arguments?.getString("travelId") ?: ""

        TravelSearchRoute(
            isInit = string.contains("isInit"),
            travelId = string.replace("isInit", "").split("|").first(),
            orderNum = if (string.contains("|")) string.replace("isInit", "").split("|")[1] else "",
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTravelSearchComplete = onTravelSearchComplete,
            onTouristDetail = onTouristDetail
        )
    }
}

object TravelSearchRoute {
    private const val route = "travel_search"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}