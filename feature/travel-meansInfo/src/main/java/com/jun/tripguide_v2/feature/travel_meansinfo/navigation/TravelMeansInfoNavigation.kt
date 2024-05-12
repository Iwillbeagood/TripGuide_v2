package com.jun.tripguide_v2.feature.travel_meansinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jun.tripguide_v2.feature.travel_meansinfo.car.CarInfoRoute
import com.jun.tripguide_v2.feature.travel_meansinfo.navigation.TravelMeansInfoRoute.car_route
import com.jun.tripguide_v2.feature.travel_meansinfo.navigation.TravelMeansInfoRoute.train_route
import com.jun.tripguide_v2.feature.travel_meansinfo.train.TrainInfoRoute

fun NavController.navigateCarInfo(travelId: String) =
    navigate(TravelMeansInfoRoute.detailRoute(car_route, travelId))

fun NavController.navigateTrainInfo(travelId: String) =
    navigate(TravelMeansInfoRoute.detailRoute(train_route, travelId))

fun NavGraphBuilder.travelMeansInfoNavGraph(
    onBackClick: () -> Unit,
    onComplete: (String) -> Unit
) {
    composable(
        route = TravelMeansInfoRoute.detailRoute(car_route, "{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("id") ?: ""
        CarInfoRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onComplete = onComplete,
        )
    }
    composable(
        route = TravelMeansInfoRoute.detailRoute(train_route, "{id}"),
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->
        val travelId = navBackStackEntry.arguments?.getString("id") ?: ""
        TrainInfoRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onComplete = onComplete
        )
    }
}

object TravelMeansInfoRoute {

    const val car_route = "car_route"

    const val train_route = "train_route"

    fun detailRoute(route: String, travelId: String): String = "$route/$travelId"
}