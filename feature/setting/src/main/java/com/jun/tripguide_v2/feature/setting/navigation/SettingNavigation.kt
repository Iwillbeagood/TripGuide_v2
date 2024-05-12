package com.jun.tripguide_v2.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateSetting() {
    navigate(SettingRoute.route)
}

fun NavGraphBuilder.settingNavGraph(
) {
    composable(route = SettingRoute.route) {

    }
}

object SettingRoute {
    const val route = "setting"
}