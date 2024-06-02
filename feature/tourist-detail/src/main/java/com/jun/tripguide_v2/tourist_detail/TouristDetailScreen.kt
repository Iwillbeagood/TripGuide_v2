/**
 * https://github.com/googlemaps/android-maps-compose
 * */
package com.jun.tripguide_v2.tourist_detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.theme.DarkGray
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.tourApi.CommonInfo
import com.jun.tripguide_v2.core.model.tourApi.DetailIntro

@Composable
fun TouristDetailRoute(
    touristId: String,
    onBackClick: () -> Unit,
    viewModel: TouristDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        TouristDetailContent(
            uiState = uiState,
            scrollState = scrollState,
            onBackClick = onBackClick
        )
    }

    LaunchedEffect(touristId) {
        viewModel.fetchTouristDetail(touristId)
    }
}

@Composable
fun TouristDetailContent(
    uiState: TouristDetailUiState,
    scrollState: ScrollState,
    onBackClick: () -> Unit
) {
    when (uiState) {
        TouristDetailUiState.Loading -> CustomLoading()
        is TouristDetailUiState.Success -> TouristDetailScreen(
            scrollState = scrollState,
            commonInfo = uiState.commonInfo,
            detailIntros = uiState.detailIntros,
            onBackClick = onBackClick
        )
    }
}

@Composable
fun TouristDetailScreen(
    scrollState: ScrollState,
    commonInfo: CommonInfo,
    detailIntros: List<DetailIntro>,
    onBackClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        CustomImage(
            imageUrl = commonInfo.firstimage,
            type = ContentType.findByType(commonInfo.contenttypeid),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .wrapContentSize(align = Alignment.TopCenter),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(240.dp))
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .background(White)
                    .padding(horizontal = 15.dp),
            ) {
                TouristTitle(commonInfo.title, commonInfo.addr1)
                ScreenSection(title = "상세 정보") {
                    DetailIntroTexts(detailIntros)
                }
                ScreenSection(title = "설명") {
                    Text(
                        text = commonInfo.overview,
                        style = MaterialTheme.typography.titleSmall,
                        color = DarkGray,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                ScreenSection(title = "지도") {
                    Surface(
                        shape = MaterialTheme.shapes.medium
                    ) {
                        val location = LatLng(commonInfo.mapy.toDouble(), commonInfo.mapx.toDouble())
                        GoogleMap(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(location, 13f)
                            }
                        ) {
                            Marker(
                                state = MarkerState(location),
                                title = commonInfo.title,
                                snippet = commonInfo.title
                            )
                        }
                    }
                }
            }
        }
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(60.dp)
                .wrapContentSize(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = White,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun DetailIntroTexts(
    detailIntros: List<DetailIntro>,
) {
    detailIntros.forEach { (title, info) ->
        if (info.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "$title:",
                    style = MaterialTheme.typography.titleSmall,
                    color = DarkGray
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = info,
                    style = MaterialTheme.typography.titleSmall,
                    color = DarkGray
                )
            }
        }
    }
}

@Composable
private fun TouristTitle(
    title: String,
    address: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "",
            tint = Gray,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = address,
            maxLines = 7,
            style = MaterialTheme.typography.titleSmall,
            color = Gray
        )
    }
}

@Composable
private fun ScreenSection(
    title: String,
    content: @Composable () -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp, bottom = 10.dp)
    )
    content()
}