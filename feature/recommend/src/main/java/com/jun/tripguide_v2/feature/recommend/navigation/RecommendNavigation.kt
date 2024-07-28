package com.jun.tripguide_v2.feature.recommend.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.feature.recommend.RecommendRoute
import com.jun.tripguide_v2.navigation.MainTabRoute

fun NavController.navigateRecommend(navOptions: NavOptions) {
    navigate(MainTabRoute.RecommendTravel, navOptions)
}

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.recommendNavGraph(
    onTouristDetail: (String) -> Unit,
    goBack: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    composable<MainTabRoute.RecommendTravel> {
        RecommendRoute(
            onTouristDetail = onTouristDetail,
            goBack = goBack,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }
}
