package com.jun.tripguide_v2.feature.travelAddDialog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.feature.travelAddDialog.TouristAddRoute
import com.jun.tripguide_v2.navigation.Route

fun NavController.navigateTouristAdd(travelId: String) {
    navigate(Route.TouristAdd(travelId))
}

fun NavGraphBuilder.touristAddNavGraph(
    onBackClick: () -> Unit,
    onTouristDetail: (String) -> Unit,
    onTravelRecommendComplete: (List<Tourist>) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable<Route.TouristAdd> { navBackStackEntry ->
        val travelId = navBackStackEntry.toRoute<Route.TouristAdd>().travelId

        TouristAddRoute(
            travelId = travelId,
            onBackClick = onBackClick,
            onShowErrorSnackBar = onShowErrorSnackBar,
            onTouristDetail = onTouristDetail,
            onTravelRecommendComplete = onTravelRecommendComplete,
        )
    }
}