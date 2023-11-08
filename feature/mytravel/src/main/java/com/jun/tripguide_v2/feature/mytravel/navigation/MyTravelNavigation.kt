package com.jun.tripguide_v2.feature.mytravel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.mytravel.MyTravelRoute

fun NavController.navigateMyTravel() {
    navigate(MyTravelRoute.route)
}

fun NavGraphBuilder.myTravelNavGraph(
    onTravelClick: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(route = MyTravelRoute.route) {
        MyTravelRoute(
            onTravelClick = onTravelClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object MyTravelRoute {
    const val route = "my_travel"
}