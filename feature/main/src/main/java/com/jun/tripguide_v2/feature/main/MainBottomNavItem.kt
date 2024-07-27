package com.jun.tripguide_v2.feature.main

import androidx.compose.runtime.Composable
import com.jun.tripguide_v2.navigation.MainTabRoute
import com.jun.tripguide_v2.navigation.Route

internal enum class MainBottomNavItem(
    val title: String, val icon: Int, val route: MainTabRoute
) {
    MY_TRAVEL(
        title = "나의 여행",
        icon = R.drawable.ic_mytravel,
        MainTabRoute.MyTravel
    ),
    RECOMMEND(
        title = "추천 여행지",
        icon = R.drawable.ic_recommend,
        MainTabRoute.RecommendTravel
    ),
    SETTING(
        title = "설정",
        icon = R.drawable.ic_setting,
        MainTabRoute.RecommendTravel
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainBottomNavItem? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}