package com.jun.tripguide_v2.feature.mytravelPlan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jun.tripguide_v2.feature.mytravelPlan.MyTravelPlanRoute
import com.jun.tripguide_v2.navigation.Route

fun NavController.navigateTravelPlan(travelId: String) {
    navigate(Route.MyTravelPlanRoute(travelId))
}

fun NavGraphBuilder.myTravelPlanNavGraph(
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Route.MyTravelPlanRoute> { navBackStackEntry ->
        val travelId = navBackStackEntry.toRoute<Route.MyTravelPlanRoute>().travelId
        MyTravelPlanRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
        )
    }
}