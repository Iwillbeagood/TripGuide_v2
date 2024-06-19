package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.component.TouristLazyColumn
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.feature.travelAddDialog.component.FilterTouristDialog
import kotlinx.coroutines.launch

@Composable
fun TouristRecommendRoute(
    onTouristDetail: (Tourist) -> Unit,
    selectTourist: (Tourist) -> Unit,
    unSelectTourist: (Tourist) -> Unit,
    viewModel: TouristRecommendViewModel = hiltViewModel()
) {
    val uiState by viewModel.recommendUiState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    LaunchedEffect(listState.canScrollForward) {
        if (!listState.canScrollForward) {
            viewModel.fetchNextPageTourist()
        }
    }

    TravelRecommendContent(
        uiState = uiState,
        listState = listState,
        viewModel = viewModel,
        selectTourist = selectTourist,
        unSelectTourist = unSelectTourist,
        onTouristDetail = onTouristDetail
    )
}

@Composable
private fun TravelRecommendContent(
    uiState: TravelRecommendUiState,
    listState: LazyListState,
    viewModel: TouristRecommendViewModel,
    selectTourist: (Tourist) -> Unit,
    unSelectTourist: (Tourist) -> Unit,
    onTouristDetail: (Tourist) -> Unit,
) {
    when (uiState) {
        TravelRecommendUiState.Loading -> CustomLoading()
        is TravelRecommendUiState.Success -> {
            TravelRecommendScreen(
                listState = listState,
                tourists = uiState.tourists,
                selectedSortType = uiState.selectedArrange.title,
                selectedTouristType = uiState.selectedContent.title,
                sortByList = uiState.arrangeTypes,
                typeByList = uiState.contentTypes,
                selectTourist = selectTourist,
                unSelectTourist = unSelectTourist,
                sortByItemClick = viewModel::changeSortBy,
                typeByItemClick = viewModel::changeTouristTypeBy,
                onTouristDetail = onTouristDetail,
                onFilterByItemClick = viewModel::fetchNewTourist
            )
        }
    }
}

@Composable
fun TravelRecommendScreen(
    listState: LazyListState,
    tourists: List<Tourist>,
    selectedSortType: String,
    selectedTouristType: String,
    sortByList: List<FilterValue>,
    typeByList: List<FilterValue>,
    unSelectTourist: (Tourist) -> Unit,
    sortByItemClick: (FilterValue) -> Unit,
    typeByItemClick: (FilterValue) -> Unit,
    onTouristDetail: (Tourist) -> Unit,
    onFilterByItemClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var showFilterDialog by remember { mutableStateOf(false) }

    TouristLazyColumn(
        listState = listState,
        touristList = tourists,
        onClickTourist = onTouristDetail,
        scrollToFirstItem = {
            scope.launch {
                listState.scrollToItem(0)
            }
        },
        onSelectTourist = unSelectTourist,
    ) {
        FilterTouristButton(
            onFilterClick = { showFilterDialog = true },
            selectedSortType = selectedSortType,
            selectedTouristType = selectedTouristType
        )
    }
    FilterTouristDialog(
        visible = showFilterDialog,
        onDismissRequest = {
            showFilterDialog = false
        },
        onFilterByItemClick = onFilterByItemClick,
        sortByList = sortByList,
        typeByList = typeByList,
        sortByItemClick = sortByItemClick,
        typeByItemClick = typeByItemClick
    )
}

@Composable
private fun FilterTouristButton(
    onFilterClick: () -> Unit,
    selectedSortType: String,
    selectedTouristType: String
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, LightGray),
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .clickable {
                onFilterClick()
            },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FilterItem(
                title = "정렬 - ",
                filterText = selectedSortType,
                icon = Icons.Default.Sort,
                modifier = Modifier.padding(15.dp)
            )
            FilterItem(
                title = "관광타입 - ",
                filterText = selectedTouristType,
                icon = Icons.Default.TravelExplore,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}

@Composable
private fun FilterItem(
    title: String,
    filterText: String,
    icon: ImageVector,
    modifier: Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(5.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            color = Black
        )
        Text(
            text = filterText,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            color = Black
        )
    }
}
