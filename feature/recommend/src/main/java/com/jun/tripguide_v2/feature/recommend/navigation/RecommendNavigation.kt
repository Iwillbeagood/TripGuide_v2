package com.jun.tripguide_v2.feature.recommend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.recommend.RecommendRoute

fun NavController.navigateRecommend() {
    navigate(RecommendRoute.route)
}

fun NavGraphBuilder.recommendNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(route = RecommendRoute.route) {
        RecommendRoute(
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object RecommendRoute {
    const val route = "recommend"
}