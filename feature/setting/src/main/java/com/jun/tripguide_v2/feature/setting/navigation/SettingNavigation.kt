package com.jun.tripguide_v2.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jun.tripguide_v2.navigation.MainTabRoute

fun NavController.navigateSetting() {
    navigate(MainTabRoute.Setting)
}

fun NavGraphBuilder.settingNavGraph(
) {
    composable<MainTabRoute.Setting> {

    }
}
