package com.jun.tripguide_v2.feature.addtravel

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.addtravel.component.TimePickerSection
import com.jun.tripguide_v2.feature.addtravel.component.TravelDurationDatePicker
import com.jun.tripguide_v2.feature.addtravel.util.toStringType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TravelInitRoute(
    onBackClick: () -> Unit,
    onAreaPickerClick: () -> Unit,
    onStartingPickerClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    destination: DestinationCode?,
    startingPoint: String?,
    onTravelInitComplete: (String) -> Unit,
    viewModel: TravelInitViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.uiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    LaunchedEffect(effect) {
        if (effect is TravelInitUiEffect.TravelInitComplete) {
            onTravelInitComplete((effect as TravelInitUiEffect.TravelInitComplete).travelId)
            viewModel.resetUiEffect()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperGray)
            .systemBarsPadding()
    ) {
        CustomTopAppBar(
            title = "여행 기본 정보",
            navigationType = TopAppBarNavigationType.Back,
            onNavigationClick = onBackClick
        )
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            ScreenSection(title = "여행지") {
                TravelInitText(
                    text = destination?.destinationString ?: "여행지를 선택해 주세요.",
                    onClick = onAreaPickerClick
                )
            }
            ScreenSection(title = "출발 장소") {
                TravelInitText(
                    text = startingPoint ?: "출발 장소를 입력해 주세요.",
                    onClick = onStartingPickerClick
                )
            }
            ScreenSection(title = "여행 일정") {
                TravelInitText(
                    text = (uiState as? TravelInitUiState.Success)?.duration?.toStringType()
                        ?: "여행 일정을 선택해 주세요.",
                    onClick = {
                        viewModel.dialogState(true)
                    }
                )
            }
            ScreenSection(title = "이동 수단") {
                TravelMeans(
                    meansItems = (uiState as TravelInitUiState.Success).meansItems,
                    onItemClick = { meansType ->
                        viewModel.meansItemPicked(meansType)
                    }
                )
            }
            TimePickerSection(
                visibility = arrayOf(
                    MeansType.CAR,
                    MeansType.PUBLIC_TRANS
                ).contains((uiState as TravelInitUiState.Success).meansItems.find { it.isSelected }?.type),
                onStartTimePicked = { viewModel.startTimePicked(it) },
                onEndTimePicked = { viewModel.endTimePicked(it) }
            )
            // "비행기, 기차 이용시 출발역, 도착역, 출발공항, 도착공항, 출발시간, 도착시간, API를 통해 입력"
            Spacer(Modifier.height(32.dp))
            TravelInitCompleteBtn(
                onClick = {
                    viewModel.addTravelComplete(
                        destination = destination ?: DestinationCode("", "", ""),
                        startingPoint = startingPoint ?: ""
                    )
                }
            )
        }
    }

    TravelDurationDatePicker(
        visibility = (effect as? TravelInitUiEffect.ShowDialogForTravelDuration)?.visibility ?: false,
        onBackClick = { viewModel.dialogState(false) },
        onTravelDurationPick = { viewModel.durationPicked(it) }
    )
}

@Composable
private fun ScreenSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
                .padding(horizontal = 15.dp)
        )
        content()
    }
    Spacer(Modifier.height(25.dp))
}

@Composable
private fun TravelInitText(
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(White)
            .heightIn(min = 56.dp)
            .border(
                width = 1.dp,
                color = Sky,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Sky,
            fontSize = 16.sp
        )
    }
}

@Composable
fun TravelInitCompleteBtn(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Sky),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(min = 56.dp)
    ) {
        Text(
            text = "선택 완료",
            fontWeight = FontWeight.Bold,
            color = White,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    TravelInitRoute(
        onBackClick = {},
        onAreaPickerClick = {},
        onStartingPickerClick = {},
        onShowErrorSnackBar = {},
        destination = null,
        startingPoint = null,
        {}
    )
}