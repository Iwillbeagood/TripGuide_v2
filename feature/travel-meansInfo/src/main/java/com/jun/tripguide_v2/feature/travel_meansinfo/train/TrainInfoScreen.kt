package com.jun.tripguide_v2.feature.travel_meansinfo.train

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CompleteBtn
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.InitText
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.surfaceDim
import com.jun.tripguide_v2.feature.travel_meansinfo.component.TrainInfoPickerDialog
import com.jun.tripguide_v2.feature.travel_meansinfo.component.TrainStationPickerDialog

@Composable
fun TrainInfoRoute(
    travelId: String,
    onBackClick: () -> Unit,
    onComplete: (String) -> Unit,
    viewModel: TrainInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.trainUiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.trainUiEffect.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiEffect) {
        if (uiEffect is TrainUiEffect.Complete) {
            onComplete((uiEffect as TrainUiEffect.Complete).travelId +  "isInit")
        }
    }

    LaunchedEffect(travelId) {
        viewModel.fetchTrainInfo(travelId)
    }

    LaunchedEffect(true) {
        viewModel.toastFlow.collect {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "이동 수단 추가 정보",
                navigationType = TopAppBarNavigationType.Back,
                onNavigationClick = onBackClick
            )
        },
        bottomBar = {
            CompleteBtn(
                onClick = viewModel::trainInfoComplete
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(it)
        ) {
            TrainInfoContent(
                uiState = uiState,
                uiEffect = uiEffect,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun TrainInfoContent(
    uiState: TrainUiState,
    uiEffect: TrainUiEffect,
    viewModel: TrainInfoViewModel
) {
    when (uiState) {
        TrainUiState.Loading -> CustomLoading()
        is TrainUiState.Success -> {
            TrainInfoScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun TrainInfoScreen(
    uiState: TrainUiState.Success,
    uiEffect: TrainUiEffect,
    viewModel: TrainInfoViewModel
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        TrainInfoSection(title = uiState.departStationTitle) {
            InitText(
                text = uiState.selectedDepartStation?.stationName ?: "출발 장소를 선택해 주세요.",
                onClick = {
                    viewModel.showTrainStationPickerDialog(
                        stations = uiState.departTrainStations,
                        onClick = { viewModel.stationSelected(it, TrainInfoType.DepartStation) },
                    )
                }
            )
        }
        TrainInfoSection(title = uiState.arriveStationTitle) {
            InitText(
                text = uiState.selectedArriveStation?.stationName ?: "여행지를 선택해 주세요.",
                onClick = {
                    viewModel.showTrainStationPickerDialog(
                        stations = uiState.arriveTrainStations,
                        onClick = { viewModel.stationSelected(it, TrainInfoType.ArriveStation) },
                    )
                }
            )
        }
        AnimatedVisibility(visible = uiState.isStationSelected) {
            Column {
                TrainInfoSection(title = uiState.trainTitle) {
                    InitText(
                        text = uiState.trainName,
                        onClick = {
                            viewModel.showTrainInfoPickerDialog(
                                onClick = { viewModel.trainSelected(it, TrainInfoType.DepartTrain) }
                            )
                        }
                    )
                }
                TrainInfoSection(title = uiState.returnTrainTitle) {
                    InitText(
                        text = uiState.returnTrainName,
                        onClick = {
                            viewModel.showTrainInfoPickerDialog(
                                isReturn = true,
                                onClick = { viewModel.trainSelected(it, TrainInfoType.ReturnTrain) }
                            )
                        }
                    )
                }
            }
        }
        TrainDialogEvents(uiEffect, viewModel)
    }
}

@Composable
fun TrainDialogEvents(
    uiEffect: TrainUiEffect,
    viewModel: TrainInfoViewModel
) {
    when (uiEffect) {
        is TrainUiEffect.ShowTrainStationPickerDialog -> {
            TrainStationPickerDialog(
                onDismissRequest = viewModel::resetUiEffect,
                list = uiEffect.stations,
                onClick = uiEffect.onClick,
            )
        }

        is TrainUiEffect.ShowTrainInfosPickerDialog -> {
            TrainInfoPickerDialog(
                onDismissRequest = viewModel::resetUiEffect,
                list = uiEffect.trains,
                onClick = uiEffect.onClick,
            )
        }

        else -> return
    }
}

@Composable
private fun TrainInfoSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
                .padding(horizontal = 15.dp)
        )
        content()
    }
    Spacer(Modifier.height(25.dp))
}
