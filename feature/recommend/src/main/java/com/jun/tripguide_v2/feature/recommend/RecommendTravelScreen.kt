package com.jun.tripguide_v2.feature.recommend

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.DarkGray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.tourApi.Festival
import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist
import com.jun.tripguide_v2.feature.recommend.utils.metersToKilometersString

@Composable
fun RecommendTravelScreen(
    uiState: RecommendUiState.Success,
    onTouristDetail: (String) -> Unit,
    locationBasedTouristListState: LazyListState,
    festivalListState: LazyListState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        CustomTopAppBar(
            title = "추천 여행지", navigationType = TopAppBarNavigationType.Nothing
        )
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
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
}

@Composable
private fun LocationBasedTouristLazyRow(
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
private fun FestivalLazyRow(
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
private fun RecommendItem(
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

@Preview
@Composable
private fun RecommendItemPreview() {
    MyTheme {
        RecommendItem(
            title = "인천펜타포트 락 페스티벌",
            type = ContentType.EventFestival,
            address = "인천광역시 연수구 센트럴로 350",
            imageUrl = "",
            modifier = Modifier
                .width(220.dp)
                .height(250.dp)
        )
    }
}