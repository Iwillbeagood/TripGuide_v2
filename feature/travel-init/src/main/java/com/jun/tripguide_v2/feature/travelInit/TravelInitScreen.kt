package com.jun.tripguide_v2.feature.travelInit

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CompleteBtn
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.InitText
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
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
    var showTravelDurationDialog by remember { mutableStateOf(false) }
    var showStartingPickerDialog by remember { mutableStateOf(false) }
    var showDestinationPickerDialog by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.initEffect.collectLatest {
            when (it) {
                is TravelInitEffect.ShowErrorSnackBar -> onShowErrorSnackBar(it.error)
                is TravelInitEffect.TravelInitComplete -> onTravelInitComplete(
                    it.travelId.toString(),
                    it.selectedMeans
                )
            }
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
            showTravelDurationDialog = { showTravelDurationDialog = true },
            showStartingPickerDialog = { showStartingPickerDialog = true },
            showDestinationPickerDialog = { showDestinationPickerDialog = true },
            meansItemPicked = viewModel::meansItemPicked
        )
    }

    if (showStartingPickerDialog) {
        StartingPickerDialog(
            onStartingPick = viewModel::startingPicked,
            onDismissRequest = {
                showStartingPickerDialog = false
            }
        )
    }

    if (showDestinationPickerDialog) {
        AreaPickerDialog(
            onAreaPicked = viewModel::destinationPicked,
            onDismissRequest = {
                showDestinationPickerDialog = false
            }
        )
    }

    if (showTravelDurationDialog) {
        DurationDatePicker(
            onBackClick = {
                showTravelDurationDialog = false
            },
            onDurationPick = {
                showTravelDurationDialog = false
                viewModel.durationPicked(it)
            }
        )
    }
}

@Composable
fun TravelInitScreen(
    modifier: Modifier,
    uiState: TravelInitUiState,
    showTravelDurationDialog: () -> Unit,
    showStartingPickerDialog: () -> Unit,
    showDestinationPickerDialog: () -> Unit,
    meansItemPicked: (MeansType) -> Unit,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        ScreenSection(title = "출발 장소") {
            InitText(
                text = uiState.startingName,
                onClick = showStartingPickerDialog
            )
        }
        ScreenSection(title = "여행지") {
            InitText(
                text = uiState.destinationName,
                onClick = showDestinationPickerDialog
            )
        }
        ScreenSection(title = "여행 일정") {
            InitText(
                text = uiState.dateDurationString,
                onClick = showTravelDurationDialog
            )
        }
        ScreenSection(title = "이동 수단") {
            TravelMeans(
                meansItems = uiState.meansItems,
                onItemClick = meansItemPicked
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