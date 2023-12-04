package com.jun.tripguide_v2.feature.mytravelPlan.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.model.Route

@Composable
fun RoutesMap(
    routes: List<Route>,
    onBackClick: () -> Unit
) {
    val selectedRoute = routes.find { it.isSelected } ?: routes.first()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent,
        shape = MaterialTheme.shapes.medium
    ) {
        val startLocation = LatLng(selectedRoute.mapY, selectedRoute.mapX)
        GoogleMap(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .wrapContentSize(Alignment.Center),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(startLocation, 12f)
            }
        ) {
            routes.forEach { route ->
                Marker(
                    state = MarkerState(LatLng(route.mapY, route.mapX)),
                    title = route.title,
                    snippet = if (route.isSelected) route.title else ""
                )
            }

            Polyline(
                points = routes.map { LatLng(it.mapY, it.mapX) },
                color = Gray
            )
        }
    }
}