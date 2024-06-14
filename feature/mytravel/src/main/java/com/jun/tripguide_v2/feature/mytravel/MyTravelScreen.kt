package com.jun.tripguide_v2.feature.mytravel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jun.tripguide_v2.core.designsystem.component.AutoSlidingCarousel
import com.jun.tripguide_v2.core.designsystem.component.CustomAlertDialog
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2.feature.mytravel.util.toDateStringType
import com.jun.tripguide_v2.feature.mytravel.util.toYearStringType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyTravelRoute(
    onTravelClick: (String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: MyTravelViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }
    val uiEffect by viewModel.uiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        MyTravelContent(
            uiState = uiState,
            onClickTravel = { onTravelClick(it.travelId.toString()) },
            onLongClickTravel = {
                showDeleteDialog = true
            }
        )
    }

    if (showDeleteDialog) {
        CustomAlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            onConfirmation = { viewModel.deleteSelectedTravel((uiEffect as MyTravelUiEffect.ShowDeleteDialog).travel) },
            dialogTitle = "여행 일정 삭제",
            dialogText = "선택한 여행 일정을 삭제하시겠습니까?"
        )
    }
}

@Composable
fun MyTravelContent(
    uiState: MyTravelUiState, onClickTravel: (Travel) -> Unit, onLongClickTravel: (Travel) -> Unit
) {
    when (uiState) {
        MyTravelUiState.Loading -> CustomLoading()
        MyTravelUiState.Empty -> MyTravelEmptyScreen()
        is MyTravelUiState.Travels -> MyTravelScreen(
            previousTravels = uiState.previousTravels,
            currentTravels = uiState.currentTravels,
            plannedTravels = uiState.planedTravels,
            onClickTravel = onClickTravel,
            onLongClickTravel = onLongClickTravel
        )
    }
}

@Composable
private fun MyTravelScreen(
    previousTravels: List<Travel>,
    currentTravels: List<Travel>,
    plannedTravels: List<Travel>,
    onClickTravel: (Travel) -> Unit,
    onLongClickTravel: (Travel) -> Unit,
) {
    LazyColumn {
        myTravelLazyColumn(
            title = "지난 여행",
            travels = previousTravels,
            onClickTravel = onClickTravel,
            onLongClickTravel = onLongClickTravel
        )
        myTravelLazyColumn(
            title = "진행중인 여행",
            travels = currentTravels,
            onClickTravel = onClickTravel,
            onLongClickTravel = onLongClickTravel
        )
        myTravelLazyColumn(
            title = "계획된 여행",
            travels = plannedTravels,
            onClickTravel = onClickTravel,
            onLongClickTravel = onLongClickTravel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.myTravelLazyColumn(
    title: String,
    travels: List<Travel>,
    onClickTravel: (Travel) -> Unit,
    onLongClickTravel: (Travel) -> Unit,
) {
    if (travels.isEmpty()) return

    item {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(70.dp)
                .padding(top = 30.dp)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
    }
    items(travels) {
        MyTravelItem(
            title = it.destination.destinationString,
            images = it.images,
            startDate = it.startDate,
            endDate = it.endDate,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 14.dp, top = 7.dp, bottom = 7.dp)
                .animateItemPlacement()
                .combinedClickable(onClick = { onClickTravel(it) },
                    onLongClick = { onLongClickTravel(it) })
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MyTravelItem(
    title: String,
    images: List<String>,
    startDate: Long,
    endDate: Long,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, LightGray),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AutoSlidingCarousel(itemsCount = images.size, itemContent = { index ->
                    CustomImage(
                        imageUrl = images[index],
                        type = ContentType.All,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                    )
                })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(White.copy(alpha = 0.7f))
                            .padding(3.dp)
                    ) {
                        Text(
                            text = DateDuration(startDate, endDate).toYearStringType(),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(2.dp)
                                .alignByBaseline()
                        )
                        Text(
                            text = DateDuration(startDate, endDate).toDateStringType(),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(2.dp)
                                .alignByBaseline()
                        )
                    }
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

