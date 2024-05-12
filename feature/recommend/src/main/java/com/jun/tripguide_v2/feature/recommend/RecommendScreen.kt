package com.jun.tripguide_v2.feature.recommend

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.theme.DarkGray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.tourApi.Festival
import com.jun.tripguide_v2.feature.recommend.utils.hasLocationPermission
import com.jun.tripguide_v2.feature.recommend.utils.metersToKilometersString
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun RecommendRoute(
    onTouristDetail: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    goBack: () -> Unit,
    viewModel: RecommendViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest {
            goBack()
            onShowErrorSnackBar(it)
        }
    }

    val locationBasedTouristListState = rememberLazyListState()
    LaunchedEffect(locationBasedTouristListState.canScrollForward) {
        if (!locationBasedTouristListState.canScrollForward) {
            viewModel.fetchNextPageLocationBasedTourist()
        }
    }

    val festivalListState = rememberLazyListState()
    LaunchedEffect(festivalListState.canScrollForward) {
        if (!festivalListState.canScrollForward) {
            viewModel.fetchNextPageFestival()
        }
    }

    InitPermission(context = context, goBack = goBack, viewModel = viewModel)

    RecommendContent(
        context = context,
        uiState = uiState,
        onTouristDetail = onTouristDetail,
        locationBasedTouristListState = locationBasedTouristListState,
        festivalListState = festivalListState
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun InitPermission(
    context: Context,
    goBack: () -> Unit,
    viewModel: RecommendViewModel
) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(!context.hasLocationPermission()) {
        permissionState.launchMultiplePermissionRequest()
    }

    when {
        permissionState.allPermissionsGranted -> {
            viewModel.fetchCurrentLocation()
        }

        permissionState.shouldShowRationale -> {
            RationaleAlert(onDismiss = goBack) {
                permissionState.launchMultiplePermissionRequest()
            }
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
            viewModel.revokedPermissions()
        }
    }
}

@Composable
fun RecommendContent(
    context: Context,
    uiState: RecommendUiState,
    onTouristDetail: (String) -> Unit,
    locationBasedTouristListState: LazyListState,
    festivalListState: LazyListState,
) {
    when (uiState) {
        RecommendUiState.Loading -> CustomLoading()
        RecommendUiState.RevokedPermissions -> RevokedScreen(context)
        is RecommendUiState.Success -> RecommendTravelScreen(
            uiState = uiState,
            onTouristDetail = onTouristDetail,
            locationBasedTouristListState = locationBasedTouristListState,
            festivalListState = festivalListState
        )
    }
}