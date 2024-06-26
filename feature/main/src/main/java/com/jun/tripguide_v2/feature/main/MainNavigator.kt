package com.jun.tripguide_v2.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.mytravel.navigation.MyTravelRoute
import com.jun.tripguide_v2.feature.mytravel.navigation.navigateMyTravel
import com.jun.tripguide_v2.feature.mytravelPlan.navigation.navigateTravelPlan
import com.jun.tripguide_v2.feature.recommend.navigation.navigateRecommend
import com.jun.tripguide_v2.feature.setting.navigation.navigateSetting
import com.jun.tripguide_v2.feature.travelInit.navigation.navigateTravelInit
import com.jun.tripguide_v2.feature.travelAddDialog.navigation.navigateTravelRecommend
import com.jun.tripguide_v2.feature.travelSearch.navigation.navigateTravelSearchRoute
import com.jun.tripguide_v2.feature.travel_meansinfo.navigation.navigateCarInfo
import com.jun.tripguide_v2.feature.travel_meansinfo.navigation.navigateTrainInfo
import com.jun.tripguide_v2.tourist_detail.navigation.navigateTouristDetail

internal class MainNavigator(
    val navController: NavHostController
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MyTravelRoute.route

    val currentItem: MainBottomNavItem?
        @Composable get() = currentDestination
            ?.route
            ?.let(MainBottomNavItem::find)

    fun navigate(item: MainBottomNavItem) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (item) {
            MainBottomNavItem.MY_TRAVEL -> navController.navigateMyTravel()
            MainBottomNavItem.RECOMMEND -> navController.navigateRecommend()
            MainBottomNavItem.SETTING -> navController.navigateSetting()
        }
    }

    fun navigateTravelInit() {
        navController.navigateTravelInit()
    }

    fun navigateMeansInfo(travelId: String, selectedMeansType: MeansType) {
        when(selectedMeansType) {
            MeansType.CAR -> navigateCarInfo(travelId)
            MeansType.TRAIN -> navigateTrainInfo(travelId)
        }
    }

    fun navigateCarInfo(travelId: String) {
        navController.navigateCarInfo(travelId)
    }

    fun navigateTrainInfo(travelId: String) {
        navController.navigateTrainInfo(travelId)
    }

    fun navigateTravelRecommend(travelId: String) {
        navController.navigateTravelRecommend(travelId)
    }

    fun navigateTravelSearch(travelId: String) {
        navController.navigateTravelSearchRoute(travelId)
    }

    fun navigateTouristDetail(touristId: String) {
        navController.navigateTouristDetail(touristId)
    }

    fun navigateTravelPlan(travelId: String) {
        navController.navigateTravelPlan(travelId)
    }

    fun popBackUntilStart() {
        navController.popBackStack(route = startDestination, inclusive = false)
    }

    fun popBackStackWithData(key: String, value: Any) {
        val previousBackStackEntry = navController.previousBackStackEntry
        previousBackStackEntry?.savedStateHandle?.set(key, value)

        popBackStackIfNotHome()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination()) {
            popBackStack()
        }
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    private fun isSameCurrentDestination() =
        navController.currentDestination?.route == MyTravelRoute.route

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val currentRoute = currentDestination?.route ?: return false
        return currentRoute in MainBottomNavItem
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}