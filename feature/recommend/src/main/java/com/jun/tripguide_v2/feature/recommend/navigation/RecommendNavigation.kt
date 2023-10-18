package com.jun.tripguide_v2.feature.recommend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateRecommend() {
    navigate(RecommendRoute.route)
}

fun NavGraphBuilder.recommendNavGraph(
) {
    composable(route = RecommendRoute.route) {

    }
}

object RecommendRoute {
    const val route = "recommend"
}