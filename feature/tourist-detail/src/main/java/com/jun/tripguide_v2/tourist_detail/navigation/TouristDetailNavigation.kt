package com.jun.tripguide_v2.tourist_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.tourist_detail.TouristDetailRoute

fun NavController.navigateTouristDetail(touristId: String) =
    navigate(TouristDetailRoute.detailRoute(touristId))

fun NavGraphBuilder.touristDetailNavGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = TouristDetailRoute.detailRoute("{touristId}"),
        arguments = listOf(
            navArgument("touristId") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val touristId = navBackStackEntry.arguments?.getString("touristId") ?: ""
        TouristDetailRoute(
            touristId = touristId,
            onBackClick = onBackClick,
        )
    }
}

object TouristDetailRoute {

    private const val route = "tourist_detail"

    fun detailRoute(touristId: String) = "$route/$touristId"
}