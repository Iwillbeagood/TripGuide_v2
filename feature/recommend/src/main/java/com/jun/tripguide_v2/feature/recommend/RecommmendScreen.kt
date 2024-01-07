package com.jun.tripguide_v2.feature.recommend

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.DarkGray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.tourApi.Festival
import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist
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
fun InitPermission(
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
        is RecommendUiState.Success -> RecommendScreen(
            uiState = uiState,
            onTouristDetail = onTouristDetail,
            locationBasedTouristListState = locationBasedTouristListState,
            festivalListState = festivalListState
        )
    }
}

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

@Composable
fun RecommendScreen(
    uiState: RecommendUiState.Success,
    onTouristDetail: (String) -> Unit,
    locationBasedTouristListState: LazyListState,
    festivalListState: LazyListState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
            .background(PaperGray)
            .systemBarsPadding()
    ) {
        CustomTopAppBar(
            title = "추천 여행지", navigationType = TopAppBarNavigationType.Nothing
        )
        ScreenSection("근처에") {
            LocationBasedTouristLazyRow(
                locationBasedTourists = uiState.locationBasedTourists,
                locationBasedTouristListState = locationBasedTouristListState,
                onClickItem = onTouristDetail
            )
        }
        ScreenSection("축제") {
            FestivalLazyRow(
                festivals = uiState.festivals,
                festivalListState = festivalListState,
                onClickItem = onTouristDetail
            )
        }
    }
}

@Composable
fun LocationBasedTouristLazyRow(
    locationBasedTourists: List<LocationBasedTourist>,
    locationBasedTouristListState: LazyListState,
    onClickItem: (String) -> Unit
) {
    LazyRow(
        state = locationBasedTouristListState,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = locationBasedTourists,
            key = { item -> item.id }
        ) { item ->
            RecommendItem(
                title = item.title,
                type = ContentType.findByType(item.type),
                address = item.address,
                distance = item.dist,
                imageUrl = item.firstImage,
                modifier = Modifier
                    .width(220.dp)
                    .height(250.dp)
                    .clickable {
                        onClickItem(item.id)
                    }
            )
        }
    }
}

@Composable
fun FestivalLazyRow(
    festivals: List<Festival>,
    festivalListState: LazyListState,
    onClickItem: (String) -> Unit
) {
    LazyRow(
        state = festivalListState,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            items = festivals,
            key = { item -> item.tel }
        ) { item ->
            RecommendItem(
                title = item.title,
                type = item.type,
                address = item.address,
                imageUrl = item.firstImage,
                modifier = Modifier
                    .width(200.dp)
                    .height(250.dp)
                    .clickable {
                        onClickItem(item.id)
                    }
            )
        }
    }
}

@Composable
fun RecommendItem(
    title: String,
    type: ContentType,
    address: String,
    imageUrl: String,
    modifier: Modifier,
    distance: Double = 0.0,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small),
    ) {
        CustomImage(
            imageUrl = imageUrl,
            type = type,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .wrapContentSize(Alignment.BottomStart)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelMedium,
                color = White
            )
            Text(text = address, style = MaterialTheme.typography.bodySmall, color = LightGray)
        }
        if (distance != 0.0) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                Text(
                    text = metersToKilometersString(distance),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge,
                    color = DarkGray,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .background(White.copy(alpha = 0.7f))
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
private fun ScreenSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = DarkGray,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .paddingFromBaseline(top = 30.dp, bottom = 20.dp)
        )
        content()
    }
    Spacer(Modifier.height(25.dp))
}

@Composable
fun RationaleAlert(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "권한 설정을 하지 않으면 추천 여행지를 확인하실 수 없습니다.",
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }
        }
    }
}
