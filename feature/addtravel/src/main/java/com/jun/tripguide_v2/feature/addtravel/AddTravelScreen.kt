package com.jun.tripguide_v2.feature.addtravel

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import com.jun.tripguide_v2.core.model.MeansItems
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.ui.BackButtonAndTitle

@Composable
fun AddTravelRoute(
    onBackClick: () -> Unit,
    onPickDestinationClick: () -> Unit,
    onPickStartingPointClick: () -> Unit,
    destination: String?,
    startingPoint: String?,
    viewModel: AddTravelViewModel = hiltViewModel()
) {

    val addTravelUiState by viewModel.addTravelUiState.collectAsStateWithLifecycle()
    val effect by viewModel.addTravelUiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(effect) {

    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            BackButtonAndTitle(onBackClick = onBackClick, title = "여행 기본 정보")
            ScreenSection(title = "여행지") {
                AddTravelText(
                    text = destination ?: "여행지를 선택해 주세요.",
                    onClick = onPickDestinationClick
                )
            }
            ScreenSection(title = "출발 장소") {
                AddTravelText(
                    text = startingPoint ?: "출발 장소를 입력해 주세요.",
                    onClick = onPickStartingPointClick
                )
            }
            ScreenSection(title = "여행 일정") {
                DatePicker()
            }
            ScreenSection(title = "이동 수단") {
                TravelMeans(
                    meansItems = (addTravelUiState as AddTravelUiState.MeansItemState).meansItems
                ) { selectedType ->

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
    Spacer(Modifier.height(16.dp))
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .paddingFromBaseline(top = 60.dp, bottom = 16.dp)
                .padding(horizontal = 15.dp)
        )
        content()
    }
}

@Composable
private fun AddTravelText(
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
            color = Sky,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun DatePicker() {

}

@Composable
private fun TravelMeans(
    meansItems: List<MeansItems>,
    onItemClick: (MeansType) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(meansItems) { item ->
            MeansItem(
                drawable = item.drawable,
                text = item.type.value,
                color = if (item.isSelected) SkyGray else LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item.type)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    AddTravelRoute(
        onBackClick = {},
        onPickDestinationClick = {},
        onPickStartingPointClick = {},
        destination = null,
        startingPoint = null,
    )
}