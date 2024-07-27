package com.jun.tripguide_v2.feature.mytravel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.mytravel.MyTravelRoute
import com.jun.tripguide_v2.navigation.MainTabRoute

fun NavController.navigateMyTravel() {
    navigate(MainTabRoute.MyTravel)
}

fun NavGraphBuilder.myTravelNavGraph(
    onTravelClick: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<MainTabRoute.MyTravel> {
        MyTravelRoute(
            onTravelClick = onTravelClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}