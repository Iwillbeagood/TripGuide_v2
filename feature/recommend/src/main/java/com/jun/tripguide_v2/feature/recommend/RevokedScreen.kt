package com.jun.tripguide_v2.feature.recommend

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.feature.recommend.utils.hasLocationPermission

@Composable
fun RevokedScreen(
    context: Context
) {
    with(context) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("추천 여행지를 확인하기 위해서는 권한 설정이 필요합니다.")
            Button(
                onClick = {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                },
                enabled = !hasLocationPermission()
            ) {
                if (hasLocationPermission()) CircularProgressIndicator(
                    modifier = Modifier.size(14.dp),
                    color = Color.White
                )
                else Text("설정")
            }
        }
    }
}