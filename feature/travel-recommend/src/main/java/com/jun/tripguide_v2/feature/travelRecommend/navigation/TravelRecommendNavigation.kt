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
    onTouristDetail: (String) -> Unit,
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
        val string = navBackStackEntry.arguments?.getString("id") ?: ""
        TravelRecommendRoute(
            isInit = string.contains("isInit"),
            travelId = string.replace("isInit", "").split("|").first(),
            orderNum = if (string.contains("|")) string.replace("isInit", "").split("|")[1] else "",
            onBackClick = onBackClick,
            onTravelRecommendComplete = onTravelRouteComplete,
            onTouristDetail = onTouristDetail,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object TravelRecommendRoute {

    private const val route = "travel_recommend"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}