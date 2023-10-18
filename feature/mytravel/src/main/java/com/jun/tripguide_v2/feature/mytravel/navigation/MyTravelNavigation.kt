package com.jun.tripguide_v2.feature.mytravel.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.mytravel.MyTravelScreen

fun NavController.navigateMyTravel() {
    navigate(MyTravelRoute.route)
}

fun NavGraphBuilder.myTravelNavGraph(

) {
    composable(route = MyTravelRoute.route) {
        MyTravelScreen(
        )
    }
}

object MyTravelRoute {
    const val route = "my_travel"
}