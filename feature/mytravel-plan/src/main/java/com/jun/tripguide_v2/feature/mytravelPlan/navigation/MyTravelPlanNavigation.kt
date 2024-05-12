package com.jun.tripguide_v2.feature.mytravelPlan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.feature.mytravelPlan.MyTravelPlanRoute

fun NavController.navigateTravelPlan(travelId: String) {
    navigate(MyTravelPlanRoute.detailRoute(travelId))
}

fun NavGraphBuilder.myTravelPlanNavGraph(
    onBackClick: () -> Unit,
    onSearchRoute: (String) -> Unit,
    onRecommendRoute: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(
        route = MyTravelPlanRoute.detailRoute("{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("id")
        MyTravelPlanRoute(
            travelId = travelId ?: "",
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onSearchRoute = onSearchRoute,
            onRecommendRoute = onRecommendRoute
        )
    }
}

object MyTravelPlanRoute {
    const val route = "my_travel_plan"

    fun detailRoute(travelId: String): String = "$route/$travelId"
}