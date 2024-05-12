package com.jun.tripguide_v2.feature.travel_meansinfo.car

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.jun.tripguide_v2.core.designsystem.component.CompleteBtn
import com.jun.tripguide_v2.core.designsystem.component.CustomTimePicker
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.ScreenSection
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import java.time.LocalTime

@Composable
fun CarInfoRoute(
    travelId: String,
    onBackClick: () -> Unit,
    onComplete: (String) -> Unit,
    viewModel: CarInfoViewModel = hiltViewModel()
) {
    CarInfoScreen(
        travelId = travelId,
        onBackClick = onBackClick,
        onMeansInfoComplete = { onComplete(travelId + "isInit") },
        startTimePicked = viewModel::startTimePicked,
        carInfoComplete = viewModel::carInfoComplete
    )
}

@Composable
private fun CarInfoScreen(
    travelId: String,
    onBackClick: () -> Unit,
    onMeansInfoComplete: () -> Unit,
    startTimePicked: (LocalTime) -> Unit,
    carInfoComplete: (String) -> Unit
) {
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
                onClick = {
                    carInfoComplete(travelId)
                    onMeansInfoComplete()
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
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
}

@Preview(showBackground = true)
@Composable
private fun CarInfoScreenPreview() {
    MyTheme {
        Column {
            CarInfoScreen(
                travelId = "",
                onBackClick = {  },
                onMeansInfoComplete = {  },
                startTimePicked = {}
            ) {

            }
        }
    }
}