package com.jun.tripguide_v2.feature.travelInit.areapicker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.model.tourApi.AreaCode

@Composable
fun AreaPickerRoute(
    onBackClick: () -> Unit,
    onBackClickWithData: (AreaCode, AreaCode) -> Unit,
    viewModel: AreaPickerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.uiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(effect) {
        val defaultAreaCode = (effect as? AreaPickerUiEffect.DestinationAreaCodePicked)?.defaultAreaCode
        val areaCode = (effect as? AreaPickerUiEffect.DestinationAreaCodePicked)?.areaCode

        if (defaultAreaCode != null && areaCode != null)
            onBackClickWithData(defaultAreaCode, areaCode)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CustomTopAppBar(
            title = "여행지",
            navigationType = TopAppBarNavigationType.Close,
            onNavigationClick = onBackClick
        )
        AreaCodeContent(
            uiState = uiState,
            onClickAreaItem = { viewModel.areaPicked(it) },
            onClickDefaultAreaCodeItem = { viewModel.upDateDefaultAreaCodeState(it) }
        )
    }

}

@Composable
fun AreaCodeContent(
    uiState: AreaPickerUiState,
    onClickAreaItem: (AreaCode) -> Unit,
    onClickDefaultAreaCodeItem: (AreaCode) -> Unit
) {
    when (uiState) {
        is AreaPickerUiState.Loading -> CustomLoading()
        is AreaPickerUiState.AreaCodes -> {
            AreaCodeScreen(
                defaultAreaCodes = uiState.defaultAreaCodes,
                areaCodes = uiState.areaCodes,
                onClickAreaItem = onClickAreaItem,
                onClickDefaultAreaCodeItem = onClickDefaultAreaCodeItem
            )
        }
    }
}

@Composable
fun AreaCodeScreen(
    defaultAreaCodes: List<AreaCode>,
    areaCodes: List<AreaCode>,
    onClickAreaItem: (AreaCode) -> Unit,
    onClickDefaultAreaCodeItem: (AreaCode) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        AreaCodeItemsColumn(
            areaCodes = defaultAreaCodes,
            onClickAreaItem = { onClickDefaultAreaCodeItem(it) },
            modifier = Modifier.weight(1.5f)
        )
        Divider(
            color = Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        AreaCodeItemsColumn(
            areaCodes = areaCodes,
            onClickAreaItem = { onClickAreaItem(it) },
            isDefaultAreaCodeSelected = true,
            modifier = Modifier.weight(2f)
        )

    }
}

@Composable
fun AreaCodeItemsColumn(
    areaCodes: List<AreaCode>,
    onClickAreaItem: (AreaCode) -> Unit,
    isDefaultAreaCodeSelected: Boolean = false,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = areaCodes.isNotEmpty(),
        enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) + fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) + fadeOut(),
        modifier = modifier
    ) {
        LazyColumn {
            items(
                items = areaCodes,
                key = { item -> item.code }
            ) { item ->
                AreaItem(
                    text = item.name,
                    textColor = if (item.isSelected || isDefaultAreaCodeSelected) Sky else Gray,
                    color = if (item.isSelected || isDefaultAreaCodeSelected) PaperGray else LightGray,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onClickAreaItem(item)
                        }
                )
                Divider(
                    color = Black,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                )
            }
        }

    }
}

@Composable
fun AreaItem(
    text: String,
    textColor: Color,
    color: Color,
    modifier: Modifier
) {
    Box {
        Text(
            text = text,
            color = textColor,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .background(color)
                .padding(16.dp)
        )
    }
}