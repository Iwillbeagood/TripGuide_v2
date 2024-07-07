package com.jun.tripguide_v2.feature.travelAddDialog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.feature.travelAddDialog.TouristAddRoute

fun NavController.navigateTouristAdd(travelId: String) {
    navigate(TouristAddRoute.detailRoute(travelId))
}

fun NavGraphBuilder.touristAddNavGraph(
    onBackClick: () -> Unit,
    onTouristDetail: (String) -> Unit,
    onTravelRecommendComplete: (List<Tourist>) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(
        route = TouristAddRoute.detailRoute("{travelId}"),
        arguments = listOf(
            navArgument("travelId") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("travelId") ?: ""

        TouristAddRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTouristDetail = onTouristDetail,
            onTravelRecommendComplete = onTravelRecommendComplete,
        )
    }
}

object TouristAddRoute {
    private const val route = "tourist_add"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}