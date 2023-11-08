package com.jun.tripguide_v2.feature.mytravel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.AutoSlidingCarousel
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.Duration
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2.feature.mytravel.util.toDateStringType
import com.jun.tripguide_v2.feature.mytravel.util.toYearStringType
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyTravelRoute(
    onTravelClick: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: MyTravelViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    Column(
        modifier = Modifier.fillMaxSize()
        .background(PaperGray)
        .systemBarsPadding()
    ) {
        CustomTopAppBar(
            title = "나의 여행",
            navigationType = TopAppBarNavigationType.Nothing
        )
        MyTravelContent(
            uiState = uiState,
            onClickTravel = { onTravelClick(it.travelId) }
        )
    }
}

@Composable
fun MyTravelContent(
    uiState: MyTravelUiState,
    onClickTravel: (Travel) -> Unit
) {
    when (uiState) {
        MyTravelUiState.Empty -> MyTravelEmptyScreen()
        is MyTravelUiState.Travels ->
            MyTravelScreen(
                previousTravels = uiState.previousTravels,
                currentTravels = uiState.currentTravels,
                plannedTravels = uiState.planedTravels,
                onClickTravel = onClickTravel
            )
    }
}

@Composable
fun MyTravelScreen(
    previousTravels: List<Travel>,
    currentTravels: List<Travel>,
    plannedTravels: List<Travel>,
    onClickTravel: (Travel) -> Unit,
) {
    LazyColumn {
        myTravelLazyColumn(title = "지난 여행", travels = previousTravels, onClickTravel = onClickTravel)
        myTravelLazyColumn(title = "진행중인 여행", travels = currentTravels, onClickTravel = onClickTravel)
        myTravelLazyColumn(title = "계획된 여행", travels = plannedTravels, onClickTravel = onClickTravel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.myTravelLazyColumn(
    title: String,
    travels: List<Travel>,
    onClickTravel: (Travel) -> Unit
) {
    if (travels.isEmpty()) return

    item {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .paddingFromBaseline(top = 10.dp, bottom = 10.dp)
                    .padding(horizontal = 15.dp)
            )
        }
    }
    items(
        travels,
        key = { item -> item.travelId }
    ) {
        MyTravelItem(
            title = it.title,
            images = it.images,
            startDate = it.startDate,
            endDate = it.endDate,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 14.dp, top = 7.dp, bottom = 7.dp)
                .animateItemPlacement()
                .clickable {
                    onClickTravel(it)
                }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTravelItem(
    title: String,
    images: List<String>,
    startDate: Long,
    endDate: Long,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, LightGray),
        color = White,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AutoSlidingCarousel(
                    itemsCount = images.size,
                    itemContent = { index ->
                        CoilImage(
                            imageModel = { images[index] },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                            ),
                            component = rememberImageComponent {
                                + CrossfadePlugin(
                                    duration = 550
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            loading = {
                                Box(modifier = Modifier.matchParentSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            },
                            failure = {
                                Text(text = "이미지 로딩에 실패했습니다.")
                            }
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth()
                        .background(White.copy(alpha = 0.7f))
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = Duration(startDate, endDate).toYearStringType(),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        modifier = Modifier
                            .padding(2.dp)
                            .alignByBaseline()
                    )
                    Text(
                        text = Duration(startDate, endDate).toDateStringType(),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        modifier = Modifier
                            .padding(2.dp)
                            .alignByBaseline()
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun MyTravelEmptyScreen() {
    Text(
        text = "여행 일정이 없습니다.",
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    )
}