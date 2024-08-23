package com.jun.tripguide_v2.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.jun.tripguide_v2.feature.mytravel.navigation.navigateMyTravel
import com.jun.tripguide_v2.feature.mytravelPlan.navigation.navigateTravelPlan
import com.jun.tripguide_v2.feature.recommend.navigation.navigateRecommend
import com.jun.tripguide_v2.feature.setting.navigation.navigateSetting
import com.jun.tripguide_v2.feature.travelInit.navigation.navigateTravelInit
import com.jun.tripguide_v2.navigation.MainTabRoute
import com.jun.tripguide_v2.navigation.Route
import com.jun.tripguide_v2.tourist_detail.navigation.navigateTouristDetail

internal class MainNavigator(
    val navController: NavHostController
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainBottomNavItem.MY_TRAVEL.route

    val currentItem: MainBottomNavItem?
        @Composable get() = MainBottomNavItem.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(item: MainBottomNavItem) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (item) {
            MainBottomNavItem.MY_TRAVEL -> navController.navigateMyTravel(navOptions)
            MainBottomNavItem.RECOMMEND -> navController.navigateRecommend(navOptions)
            MainBottomNavItem.SETTING -> navController.navigateSetting(navOptions)
        }
    }

    fun navigateTravelInit() {
        navController.navigateTravelInit()
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
        if (!isSameCurrentDestination<MainTabRoute.MyTravel>()) {
            popBackStack()
        }
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun shouldShowBottomBar() = MainBottomNavItem.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}