package com.jun.tripguide_v2.feature.travelInit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.travelInit.TravelInitRoute
import com.jun.tripguide_v2.navigation.Route

fun NavController.navigateTravelInit() {
    navigate(Route.TravelInit)
}

fun NavGraphBuilder.travelInitNavGraph(
    onBackClick: () -> Unit,
    onTravelInitComplete: (String, MeansType) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable<Route.TravelInit> {
        TravelInitRoute(
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTravelInitComplete = onTravelInitComplete
        )
    }
}
