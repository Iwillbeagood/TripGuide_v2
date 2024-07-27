package com.jun.tripguide_v2.tourist_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jun.tripguide_v2.navigation.Route
import com.jun.tripguide_v2.tourist_detail.TouristDetailRoute

fun NavController.navigateTouristDetail(touristId: String) =
    navigate(Route.TouristDetail(touristId))

fun NavGraphBuilder.touristDetailNavGraph(
    onBackClick: () -> Unit,
) {
    composable<Route.TouristDetail> { navBackStackEntry ->
        val touristId = navBackStackEntry.toRoute<Route.TouristDetail>().touristId

        TouristDetailRoute(
            touristId = touristId,
            onBackClick = onBackClick,
        )
    }
}