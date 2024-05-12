package com.jun.tripguide_v2.feature.main

import com.jun.tripguide_v2.feature.mytravel.navigation.MyTravelRoute
import com.jun.tripguide_v2.feature.recommend.navigation.RecommendRoute
import com.jun.tripguide_v2.feature.setting.navigation.SettingRoute

internal enum class MainBottomNavItem(
    val title: String, val icon: Int, val route: String
) {
    MY_TRAVEL(
        title = "나의 여행",
        icon = R.drawable.ic_mytravel,
        MyTravelRoute.route
    ),
    RECOMMEND(
        title = "추천 여행지",
        icon = R.drawable.ic_recommend,
        RecommendRoute.route
    ),
    SETTING(
        title = "설정",
        icon = R.drawable.ic_setting,
        SettingRoute.route
    );

    companion object {
        operator fun contains(route: String): Boolean {
            return values().map { it.route }.contains(route)
        }

        fun find(route: String): MainBottomNavItem? {
            return values().find { it.route == route }
        }
    }
}