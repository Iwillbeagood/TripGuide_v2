package com.jun.tripguide_v2.feature.travelSearch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.component.CustomSearchView
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.ScrollUpButton
import com.jun.tripguide_v2.core.designsystem.component.SelectedTourist
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.component.TouristItem
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.Tourist
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TravelSearchRoute(
    isInit: Boolean,
    travelId: String,
    orderNum: String,
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onTravelSearchComplete: () -> Unit,
    onTouristDetail: (String) -> Unit,
    viewModel: TravelSearchViewModel = hiltViewModel()
) {

    val uiState by viewModel.travelSearchUiState.collectAsStateWithLifecycle()
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is TravelSearchUiEvent.ShowErrorSnackBar -> onShowErrorSnackBar(it.throwable)
                TravelSearchUiEvent.ScrollToFirstItem -> listState.scrollToItem(0)
                TravelSearchUiEvent.TravelSearchComplete -> {
                    if (isInit) {
                        onTravelSearchComplete()
                    } else {
                        onBackClick()
                    }
                }
            }
        }
    }

    LaunchedEffect(travelId) {
        viewModel.setTravelId(travelId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()
    ) {
        CustomTopAppBar(
            title = "여행 장소 검색",
            navigationType = TopAppBarNavigationType.Back,
            onNavigationClick = onBackClick,
            actionButtons = {
                IconButton(
                    onClick = { viewModel.travelSearchComplete(orderNum.toIntOrNull() ?: 0) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                }
            }
        )
        CustomSearchView(
            value = keyword,
            onSearch = viewModel::searchTourist,
            onValueChange = viewModel::keywordChange,
            onValueClear = viewModel::clearKeyword,
            keyboardController = keyboardController
        )
        Spacer(modifier = Modifier.height(5.dp))
        TravelSearchContent(
            uiState = uiState,
            listState = listState,
            scrollToFirstItem = { viewModel.scrollToFirstItem() },
            onSelectTourist = { tourist -> viewModel.changeTouristSelection(tourist) },
            onTouristDetail = { onTouristDetail(it.id) }
        )
    }
}

@Composable
fun TravelSearchContent(
    uiState: TravelSearchUiState,
    listState: LazyListState,
    scrollToFirstItem: () -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    onTouristDetail: (Tourist) -> Unit
) {
    when (uiState) {
        TravelSearchUiState.Empty -> CustomLoading()
        is TravelSearchUiState.Success -> {
            TravelSearchScreen(
                listState = listState,
                scrollToFirstItem = scrollToFirstItem,
                touristList = uiState.tourists,
                selectedTouristList = uiState.selectedTourists,
                onSelectTourist = onSelectTourist,
                onTouristDetail = onTouristDetail
            )
        }
    }
}

@Composable
fun TravelSearchScreen(
    listState: LazyListState,
    scrollToFirstItem: () -> Unit,
    touristList: List<Tourist>,
    selectedTouristList: List<Tourist>,
    onSelectTourist: (Tourist) -> Unit,
    onTouristDetail: (Tourist) -> Unit,
) {
    SelectedTourist(
        visible = selectedTouristList.isNotEmpty(),
        selectedTouristList = selectedTouristList,
        onClickSelectedTourist = onSelectTourist
    )
    TouristLazyColumn(
        listState = listState,
        touristList = touristList,
        onClickTourist = onTouristDetail,
        scrollToFirstItem = scrollToFirstItem,
        onSelectTourist = onSelectTourist,
    )
}

@Composable
private fun TouristLazyColumn(
    listState: LazyListState,
    touristList: List<Tourist>,
    onClickTourist: (Tourist) -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    scrollToFirstItem: () -> Unit,
) {
    Box {
        LazyColumn(state = listState) {
            items(
                items = touristList,
                key = { item -> item.id }
            ) { tourist ->
                TouristItem(
                    title = tourist.title,
                    type = ContentType.findByType(tourist.type),
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
        ScrollUpButton(
            visible = listState.canScrollBackward,
            icon = Icons.Default.KeyboardArrowUp,
            onClicked = scrollToFirstItem
        )
    }
}
