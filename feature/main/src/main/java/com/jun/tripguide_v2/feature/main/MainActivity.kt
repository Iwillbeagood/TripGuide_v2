package com.jun.tripguide_v2.feature.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigator : MainNavigator = rememberMainNavigator()

            MyTheme {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}