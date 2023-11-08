package com.jun.tripguide_v2.feature.travelRecommend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.feature.travelRecommend.TravelRecommendRoute

fun NavController.navigateTravelRecommend(travelId: String) {
    navigate(TravelRecommendRoute.detailRoute(travelId))
}

fun NavGraphBuilder.travelRecommendNavGraph(
    onBackClick: () -> Unit,
    onTravelRouteComplete: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(
        route = TravelRecommendRoute.detailRoute("{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("id")
        TravelRecommendRoute(
            travelId = travelId ?: "",
            onBackClick = onBackClick,
            onTravelRecommendComplete = onTravelRouteComplete,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object TravelRecommendRoute {
    private const val route = "travel_recommend"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}