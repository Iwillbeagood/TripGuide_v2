package com.jun.tripguide_v2.feature.recommend.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.recommend.RecommendRoute

fun NavController.navigateRecommend() {
    navigate(RecommendRoute.route)
}

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.recommendNavGraph(
    onTouristDetail: (String) -> Unit,
    goBack: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable(route = RecommendRoute.route) {
        RecommendRoute(
            onTouristDetail = onTouristDetail,
            goBack = goBack,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}

object RecommendRoute {
    const val route = "recommend"
}