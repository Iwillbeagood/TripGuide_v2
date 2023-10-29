package com.jun.tripguide_v2.feature.travelroute

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.feature.travelroute.component.FilterTouristDialog

@Composable
fun TravelRoute(
    travelId: String,
    onBackClick: () -> Unit,
    viewModel: TravelRouteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    LaunchedEffect(listState.canScrollForward) {
        if (!listState.canScrollForward) {
            viewModel.fetchNextPageTourist()
        }
    }

    LaunchedEffect(uiEffect) {
        if (uiEffect is TravelRouteUiEffect.ScrollToFirstItem) {
            listState.scrollToItem(0)
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
            title = "추천 여행 장소",
            navigationType = TopAppBarNavigationType.Back,
            onNavigationClick = onBackClick,
            actionButtons = {
                IconButton(
                    onClick = {  },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                    )
                }
            }
        )
        Box {
            TravelRouteContent(
                uiState = uiState,
                listState = listState,
                onFilterClick = { viewModel.changeDialogVisibility() },
                onSelectTourist = { tourist -> viewModel.changeTouristSelection(tourist) },
                onDialogBackClick = { viewModel.changeDialogVisibility() },
                onFilterByItemClick = { viewModel.fetchNewTourist(isFilter = true) } ,
                sortByItemClick = { value -> viewModel.changeSortBy(value) },
                typeByItemClick = { value -> viewModel.changeTouristTypeBy(value) }
            )
            ScrollToFirstItemButton(
                visible = listState.canScrollBackward,
                icon = Icons.Default.KeyboardArrowUp,
                onClicked = {
                    viewModel.scrollToFirstItem()
                }
            )
        }
    }

    LaunchedEffect(travelId) {
        viewModel.fetchTourist(travelId)
    }
}

@Composable
private fun TravelRouteContent(
    uiState: TravelRouteUiState,
    listState: LazyListState,
    onFilterClick: () -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    onDialogBackClick: () -> Unit,
    onFilterByItemClick: () -> Unit,
    sortByItemClick: (FilterValue) -> Unit,
    typeByItemClick: (FilterValue) -> Unit,
) {
    when (uiState) {
        TravelRouteUiState.Loading -> TravelRouteLoading()
        is TravelRouteUiState.Success -> {
            TravelRouteScreen(
                listState = listState,
                touristList = uiState.touristList,
                selectedTouristList = uiState.selectedTourist,
                visible = uiState.dialogVisibility,
                sortByList = uiState.sortByList,
                selectedSortType = uiState.sortByList.find { it.isSelected }?.title ?: "조회수",
                selectedTouristType = uiState.touristTypeList.find { it.isSelected }?.title ?: "전체",
                sortByItemClick = sortByItemClick,
                typeByList = uiState.touristTypeList,
                typeByItemClick = typeByItemClick,
                onFilterClick = onFilterClick,
                onSelectTourist = onSelectTourist,
                onDialogBackClick = onDialogBackClick,
                onFilterByItemClick = onFilterByItemClick,
            )
        }
    }
}

@Composable
private fun TravelRouteScreen(
    listState: LazyListState,
    touristList: List<Tourist>,
    selectedTouristList: List<Tourist>,
    visible: Boolean,
    sortByList: List<FilterValue>,
    typeByList: List<FilterValue>,
    selectedSortType: String,
    selectedTouristType: String,
    sortByItemClick: (FilterValue) -> Unit,
    typeByItemClick: (FilterValue) -> Unit,
    onFilterClick: () -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    onDialogBackClick: () -> Unit,
    onFilterByItemClick: () -> Unit,
) {
    if (selectedTouristList.isNotEmpty()) {
        SelectedTourist(
            selectedTouristList = selectedTouristList,
            onClickSelectedTourist = onSelectTourist
        )
        Spacer(modifier = Modifier.height(7.dp))
    }
    TouristLazyColumn(
        listState = listState,
        touristList = touristList,
        onClickTourist = { TODO() },
        onSelectTourist = onSelectTourist
    ) {
        FilterTourist(
            onFilterClick = onFilterClick,
            selectedSortType = selectedSortType,
            selectedTouristType = selectedTouristType
        )
    }
    FilterTouristDialog(
        visible = visible,
        onBackClick = onDialogBackClick,
        onFilterByItemClick = onFilterByItemClick,
        sortByList = sortByList,
        sortByItemClick = sortByItemClick,
        typeByList = typeByList,
        typeByItemClick = typeByItemClick,
    )
}

@Composable
private fun TravelRouteLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FilterTourist(
    onFilterClick: () -> Unit,
    selectedSortType: String,
    selectedTouristType: String
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
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

@Composable
private fun SelectedTourist(
    selectedTouristList: List<Tourist>,
    onClickSelectedTourist: (Tourist) -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
    ) {
        LazyRow {
            items(
                items = selectedTouristList,
                key = { item -> item.id }
            ) { tourist ->
                SelectedTouristItem(
                    title = tourist.title,
                    imageUrl = tourist.firstImage,
                    modifier = Modifier
                        .clickable {
                            onClickSelectedTourist(tourist)
                        }
                )
            }
        }
    }
}

@Composable
private fun TouristLazyColumn(
    listState: LazyListState,
    touristList: List<Tourist>,
    onClickTourist: (Tourist) -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    content: @Composable () -> Unit
) {
    LazyColumn(state = listState) {
        item {
            content()
        }

        items(
            items = touristList,
            key = { item -> item.id }
        ) { tourist ->
            TouristItem(
                title = tourist.title,
                address = tourist.address,
                imageUrl = tourist.firstImage,
                selected = tourist.isSelected,
                onSelectTourist = { onSelectTourist(tourist) },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClickTourist(tourist)
                    }
            )
        }
    }
}

@Composable
fun ScrollToFirstItemButton(
    visible: Boolean,
    icon: ImageVector,
    onClicked: () -> Unit
) {
    if (!visible) return

    Button(
        shape = CircleShape,
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(Sky.copy(alpha = 0.5f)),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomEnd)
    )  {
        Icon(icon, null)
    }
}