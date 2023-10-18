package com.jun.tripguide_v2.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jun.tripguide_v2.core.designsystem.theme.TravelGuideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigator : MainNavigator = rememberMainNavigator()

            TravelGuideTheme {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}