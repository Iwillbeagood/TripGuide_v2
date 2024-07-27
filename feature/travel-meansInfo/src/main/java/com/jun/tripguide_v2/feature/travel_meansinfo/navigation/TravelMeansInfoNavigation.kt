package com.jun.tripguide_v2.feature.travel_meansinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jun.tripguide_v2.feature.travel_meansinfo.car.CarInfoRoute
import com.jun.tripguide_v2.feature.travel_meansinfo.train.TrainInfoRoute
import com.jun.tripguide_v2.navigation.Route

fun NavController.navigateCarInfo(travelId: String) =
    navigate(Route.CarInformation(travelId))

fun NavController.navigateTrainInfo(travelId: String) =
    navigate(Route.TrainInformation(travelId))

fun NavGraphBuilder.travelMeansInfoNavGraph(
    onBackClick: () -> Unit,
    onComplete: (String) -> Unit
) {
    composable<Route.CarInformation> { navBackStackEntry ->
        val travelId = navBackStackEntry.toRoute<Route.CarInformation>().travelId
        CarInfoRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onComplete = onComplete,
        )
    }

    composable<Route.TrainInformation> { navBackStackEntry ->
        val travelId = navBackStackEntry.toRoute<Route.TrainInformation>().travelId
        TrainInfoRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onComplete = onComplete
        )
    }
}