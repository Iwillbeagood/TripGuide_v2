package com.jun.tripguide_v2.feature.travelInit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.jun.tripguide_v2.core.designsystem.component.CustomTimePicker
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansType.CAR
import com.jun.tripguide_v2.core.model.MeansType.PLANE
import com.jun.tripguide_v2.core.model.MeansType.TRAIN
import com.jun.tripguide_v2.core.model.StartingPoint
import com.jun.tripguide_v2.core.model.TrainStation
import com.jun.tripguide_v2.feature.travelInit.component.DurationDatePicker
import com.jun.tripguide_v2.feature.travelInit.component.TravelMeans
import com.jun.tripguide_v2.feature.travelInit.util.toStringType
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalTime

@Composable
fun TravelInitRoute(
    onBackClick: () -> Unit,
    onAreaPickerClick: () -> Unit,
    onStartingPickerClick: () -> Unit,
    destination: DestinationCode?,
    startingPoint: StartingPoint?,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onTravelInitComplete: (String) -> Unit,
    viewModel: TravelInitViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    LaunchedEffect(uiEffect) {
        if (uiEffect is TravelInitUiEffect.TravelInitComplete) {
            onTravelInitComplete((uiEffect as TravelInitUiEffect.TravelInitComplete).travelId + "isInit")
            viewModel.resetUiEffect()
        }
    }

    LaunchedEffect(destination) {
        if (destination != null) {
            viewModel.fetchArriveTrainStations(destination)
        }
    }

    LaunchedEffect(startingPoint) {
        if (startingPoint != null) {
            viewModel.fetchDepartTrainStations(startingPoint)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperGray)
            .systemBarsPadding()
    ) {
        TravelInitScreen(
            uiState = uiState as TravelInitUiState.Success,
            onBackClick = onBackClick,
            onAreaPickerClick = onAreaPickerClick,
            onStartingPickerClick = onStartingPickerClick,
            destination = destination,
            startingPoint = startingPoint,
            viewModel = viewModel
        )
    }

    AnimatedVisibility(uiEffect is TravelInitUiEffect.ShowTravelDurationDialog) {
        DurationDatePicker(
            onBackClick = viewModel::resetUiEffect,
            onDurationPick = viewModel::durationPicked
        )
    }
}

@Composable
fun TravelInitScreen(
    uiState: TravelInitUiState.Success,
    onBackClick: () -> Unit,
    onAreaPickerClick: () -> Unit,
    onStartingPickerClick: () -> Unit,
    destination: DestinationCode?,
    startingPoint: StartingPoint?,
    viewModel: TravelInitViewModel,
    scrollState: ScrollState = rememberScrollState()
) {
    CustomTopAppBar(
        title = "여행 기본 정보",
        navigationType = TopAppBarNavigationType.Back,
        onNavigationClick = onBackClick
    )
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        ScreenSection(title = "출발 장소") {
            TravelInitText(
                text = startingPoint?.name ?: "출발 장소를 입력해 주세요.",
                onClick = onStartingPickerClick
            )
        }
        ScreenSection(title = "여행지") {
            TravelInitText(
                text = destination?.destinationString ?: "여행지를 선택해 주세요.",
                onClick = onAreaPickerClick
            )
        }
        ScreenSection(title = "여행 일정") {
            TravelInitText(
                text = uiState.dateDuration?.toStringType()
                    ?: "여행 일정을 선택해 주세요.",
                onClick = viewModel::showTravelDurationDialog
            )
        }
        ScreenSection(title = "이동 수단") {
            TravelMeans(
                meansItems = uiState.meansItems,
                onItemClick = viewModel::meansItemPicked
            )
        }
        AdditionalInfoByMeans(
            uiState = uiState,
            changeDropDownState = viewModel::changeDropDownExpanded,
            onStationClick = viewModel::arriveStationSelected,
            startTimePicked = viewModel::startTimePicked,
        )
        Spacer(Modifier.height(32.dp))
        TravelInitCompleteBtn(
            onClick = {
                viewModel.addTravelComplete(
                    destination = destination,
                    startingPoint = startingPoint
                )
            }
        )
    }
}

@Composable
fun AdditionalInfoByMeans(
    uiState: TravelInitUiState.Success,
    changeDropDownState: (StationType) -> Unit,
    onStationClick: (TrainStation, StationType) -> Unit,
    startTimePicked: (LocalTime) -> Unit,
) {
    when (uiState.selectedMeans) {
        CAR -> CarAdditionalInfo(startTimePicked)
        PLANE -> PlaneAdditionalInfo()
        TRAIN -> TrainAdditionalInfo(
            uiState = uiState,
            changeDropDownState = changeDropDownState,
            onStationClick = onStationClick
        )
    }
}

@Composable
fun CarAdditionalInfo(
    startTimePicked: (LocalTime) -> Unit
) {
    AnimatedVisibility(true) {
        ScreenSection(title = "여행 출발 시간") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTimePicker(
                    onTimePick = startTimePicked,
                    timeFormat = TimeFormat.AM_PM,
                    size = DpSize(200.dp, 128.dp)
                )
            }
        }
    }
}

@Composable
fun PlaneAdditionalInfo(
) {
    AnimatedVisibility(true) {

        ScreenSection(title = "여행 출발 시간") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

    }
}

@Composable
fun TrainAdditionalInfo(
    uiState: TravelInitUiState.Success,
    changeDropDownState: (StationType) -> Unit,
    onStationClick: (TrainStation, StationType) -> Unit,
) {
    AnimatedVisibility(true) {
        Column {
            ScreenSection(title = "출발역") {
                TravelInitText(
                    text = uiState.selectedDepartStation?.stationName
                        ?: "출발 장소를 먼저 입력해 주세요.",
                    onClick = {
                        if (uiState.arriveTrainStations.isNotEmpty())
                            changeDropDownState(StationType.DEPART)
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    DropdownMenu(
                        expanded = uiState.departDropDownExpanded,
                        onDismissRequest = { changeDropDownState(StationType.DEPART) }
                    ) {
                        uiState.departTrainStations.forEach {
                            DropdownMenuItem(
                                text = { Text(it.stationName) },
                                onClick = { onStationClick(it, StationType.DEPART) },
                            )
                        }
                    }
                }
            }
            ScreenSection(title = "여행지 역") {
                TravelInitText(
                    text = uiState.selectedArriveStation?.stationName
                        ?: "여행지를 먼저 선택해 주세요.",
                    onClick = {
                        if (uiState.arriveTrainStations.isNotEmpty())
                            changeDropDownState(StationType.ARRIVE)
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    DropdownMenu(
                        expanded = uiState.arriveDropDownExpanded,
                        onDismissRequest = { changeDropDownState(StationType.ARRIVE) }
                    ) {
                        uiState.arriveTrainStations.forEach {
                            DropdownMenuItem(
                                text = { Text(it.stationName) },
                                onClick = { onStationClick(it, StationType.ARRIVE) },
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(visible = uiState.selectedDepartStation != null) {
                ScreenSection(title = "여행가는 기차") {
                    TravelInitText(
                        text = uiState.selectedDepartStation?.stationName
                            ?: "출발 장소를 먼저 입력해 주세요.",
                        onClick = {
                            if (uiState.arriveTrainStations.isNotEmpty())
                                changeDropDownState(StationType.DEPART)
                        }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        DropdownMenu(
                            expanded = uiState.departDropDownExpanded,
                            onDismissRequest = { changeDropDownState(StationType.DEPART) }
                        ) {
                            uiState.departTrainStations.forEach {
                                DropdownMenuItem(
                                    text = { Text(it.stationName) },
                                    onClick = { onStationClick(it, StationType.DEPART) },
                                )
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(visible = uiState.selectedDepartStation != null) {
                ScreenSection(title = "돌아오는 기차") {
                    TravelInitText(
                        text = uiState.selectedDepartStation?.stationName
                            ?: "출발 장소를 먼저 입력해 주세요.",
                        onClick = {
                            if (uiState.arriveTrainStations.isNotEmpty())
                                changeDropDownState(StationType.DEPART)
                        }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        DropdownMenu(
                            expanded = uiState.departDropDownExpanded,
                            onDismissRequest = { changeDropDownState(StationType.DEPART) }
                        ) {
                            uiState.departTrainStations.forEach {
                                DropdownMenuItem(
                                    text = { Text(it.stationName) },
                                    onClick = { onStationClick(it, StationType.DEPART) },
                                )
                            }
                        }
                    }
                }
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