package com.jun.tripguide_v2.feature.travelInit

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CompleteBtn
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.InitText
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.travelInit.areapicker.AreaPickerDialog
import com.jun.tripguide_v2.feature.travelInit.component.DurationDatePicker
import com.jun.tripguide_v2.feature.travelInit.component.TravelMeans
import com.jun.tripguide_v2.feature.travelInit.startingpicker.StartingPickerDialog
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TravelInitRoute(
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onTravelInitComplete: (String, MeansType) -> Unit,
    viewModel: TravelInitViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    LaunchedEffect(uiEffect) {
        if (uiEffect is TravelInitUiEffect.TravelInitComplete) {
            onTravelInitComplete(
                (uiEffect as TravelInitUiEffect.TravelInitComplete).travelId.toString(),
                (uiEffect as TravelInitUiEffect.TravelInitComplete).selectedMeans
            )
            viewModel.resetUiEffect()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "여행 기본 정보",
                navigationType = TopAppBarNavigationType.Back,
                onNavigationClick = onBackClick
            )
        },
        bottomBar = {
            CompleteBtn(
                onClick = viewModel::addTravelComplete,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        TravelInitScreen(
            modifier = Modifier.padding(it),
            uiState = uiState,
            viewModel = viewModel,
        )
    }

    if (uiEffect is TravelInitUiEffect.ShowStartingPickerDialog) {
        StartingPickerDialog(
            onStartingPick = viewModel::startingPicked,
            onDismissRequest = viewModel::resetUiEffect
        )
    }

    if (uiEffect is TravelInitUiEffect.ShowDestinationPickerDialog) {
        AreaPickerDialog(
            onAreaPicked = viewModel::destinationPicked,
            onDismissRequest = viewModel::resetUiEffect
        )
    }

    if (uiEffect is TravelInitUiEffect.ShowTravelDurationDialog) {
        DurationDatePicker(
            onBackClick = viewModel::resetUiEffect,
            onDurationPick = viewModel::durationPicked
        )
    }
}

@Composable
fun TravelInitScreen(
    modifier: Modifier,
    uiState: TravelInitUiState,
    viewModel: TravelInitViewModel,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        ScreenSection(title = "출발 장소") {
            InitText(
                text = uiState.startingName,
                onClick = viewModel::showStartingPickerDialog
            )
        }
        ScreenSection(title = "여행지") {
            InitText(
                text = uiState.destinationName,
                onClick = viewModel::showDestinationPickerDialog
            )
        }
        ScreenSection(title = "여행 일정") {
            InitText(
                text = uiState.dateDurationString,
                onClick = viewModel::showTravelDurationDialog
            )
        }
        ScreenSection(title = "이동 수단") {
            TravelMeans(
                meansItems = uiState.meansItems,
                onItemClick = viewModel::meansItemPicked
            )
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