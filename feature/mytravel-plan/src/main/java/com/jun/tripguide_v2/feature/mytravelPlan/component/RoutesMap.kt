package com.jun.tripguide_v2.feature.mytravelPlan.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.feature.mytravel.plan.R
import com.jun.tripguide_v2.feature.mytravelPlan.util.bitmapDescriptor

@Composable
fun RoutesMap(
    routes: List<Route>,
    selectedRoute: Route
) {

    val selectedLatLng = selectedRoute.toLatLng()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedLatLng, 12f)
    }

    LaunchedEffect(selectedRoute) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(selectedLatLng, 12f, 0f, 0f)
            ),
            durationMs = 1000
        )
    }

    Surface(
        color = Color.Transparent,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .wrapContentSize(Alignment.Center),
            cameraPositionState = cameraPositionState
        ) {
            routes.forEach { route ->
                CustomMarker(
                    route = route,
                    isSelected = route == selectedRoute
                )
            }

            Polyline(
                points = routes.map { it.toLatLng() },
                color = Gray
            )
            Polyline(
                points = routes.filter { it.isSelected || it.isBeforeRouteSelected }.map { it.toLatLng() },
                color = Sky
            )
        }
    }
}

@Composable
fun CustomMarker(
    route: Route,
    isSelected: Boolean,
    markerState: MarkerState = rememberMarkerState(
        position = LatLng(route.mapY, route.mapX)
    )
) {
    val context = LocalContext.current

    Marker(
        state = markerState,
        title = route.title,
        icon = if (isSelected) bitmapDescriptor(context, R.drawable.ic_selected_location, 100)
        else bitmapDescriptor(context, R.drawable.ic_location, 100)
    )

    if (isSelected) markerState.showInfoWindow()
}


private fun Route.toLatLng() = LatLng(mapY, mapX)