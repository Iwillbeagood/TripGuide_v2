package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomSearchView
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.SelectedTourist
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.component.TouristLazyColumn
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.feature.travelAddDialog.TravelAddTabs.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun TouristAddRoute(
    travelId: String,
    onBackClick: () -> Unit,
    onTravelRecommendComplete: (List<Tourist>) -> Unit,
    onTouristDetail: (id: String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: TouristAddViewModel = hiltViewModel()
) {
    val recommendTourists by viewModel.recommendTourists.collectAsStateWithLifecycle()
    val searchTourists by viewModel.searchTourists.collectAsStateWithLifecycle()
    val selectedTourists by viewModel.selectedTourists.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val tab by viewModel.tab.collectAsStateWithLifecycle()

    LaunchedEffect(travelId) {
        viewModel.initViewModel(travelId)
    }

    LaunchedEffect(listState.canScrollForward) {
        if (!listState.canScrollForward) {
            viewModel.nextPage()
        }
    }

    LaunchedEffect(true) {
        viewModel.effectState.collectLatest {
            when (it) {
                is TravelAddDialogUiEffect.ShowErrorSnackBar -> onShowErrorSnackBar(it.throwable)
                is TravelAddDialogUiEffect.TravelRecommendComplete -> {
                    onTravelRecommendComplete(it.tourists)
                }
            }
        }
    }

    TouristScreen(
        tab = tab,
        keyword = keyword,
        onValueChange = viewModel::changeKeyword,
        filterState = filterState,
        tourists = when (tab) {
            Recommend -> recommendTourists
            Search -> searchTourists
        },
        selectedTourists = selectedTourists,
        onSelectTab = viewModel::changeTab,
        onTouristDetail = { onTouristDetail(it.id) },
        onSelectTourist = viewModel::selectedTourist,
        onBackClick = onBackClick,
        travelRecommendComplete = {

        },
        onArrangeClick = viewModel::changeArrange,
        onContentTypeClick = viewModel::changeContentType
    )
}

@Composable
fun TouristScreen(
    tab: TravelAddTabs,
    keyword: String,
    onValueChange: (String) -> Unit,
    filterState: FilterState,
    listState: LazyListState = rememberLazyListState(),
    tourists: List<Tourist>,
    selectedTourists: List<Tourist>,
    onSelectTab: (index: Int) -> Unit,
    onTouristDetail: (Tourist) -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    onBackClick: () -> Unit,
    travelRecommendComplete: () -> Unit,
    onArrangeClick: (FilterValue) -> Unit,
    onContentTypeClick: (FilterValue) -> Unit
) {
    val scope = rememberCoroutineScope()
    var showArrange by remember { mutableStateOf(false) }
    var showContentType by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "여행 장소 추가",
                    navigationType = TopAppBarNavigationType.Back,
                    onNavigationClick = onBackClick,
                    actionButtons = {
                        IconButton(
                            onClick = travelRecommendComplete,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                            )
                        }
                    }
                )
                TabRow(
                    selectedTabIndex = tab.index,
                    containerColor = MaterialTheme.colorScheme.surfaceDim,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[tab.index])
                        )
                    }
                ) {
                    TravelAddTabs.entries.forEachIndexed { index, tab ->
                        Tab(
                            selected = tab.index == index,
                            onClick = { onSelectTab(index) }
                        ) {
                            Text(
                                text = tab.title,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 15.dp)
                            )
                        }
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            SelectedTourist(
                visible = selectedTourists.isNotEmpty(),
                selectedTouristList = selectedTourists,
                onClickSelectedTourist = onSelectTourist
            )
            TouristLazyColumn(
                listState = listState,
                touristList = tourists,
                onClickTourist = onTouristDetail,
                scrollToFirstItem = {
                    scope.launch {
                        listState.scrollToItem(0)
                    }
                },
                onSelectTourist = onSelectTourist
            ) {
                TouristAddContent(
                    keyword = keyword,
                    onValueChange = onValueChange,
                    selectedArrange = filterState.selectedArrange.title,
                    selectedContentType = filterState.selectedContentType.title,
                    tab = tab,
                    onArrangeClick = {
                        showArrange = true
                    },
                    onContentTypeClick = {
                        showContentType = true
                    }
                )
            }
        }
    }

    if (showArrange) {
        FilterBottomSheet(
            filterList = filterState.arranges,
            onFilterClick = onArrangeClick,
            onDismissRequest = { showArrange = false }
        )
    }
    if (showContentType) {
        FilterBottomSheet(
            filterList = filterState.contentTypes,
            onFilterClick = onContentTypeClick,
            onDismissRequest = { showContentType = false }
        )
    }
}

enum class TravelAddTabs(val title: String, val index: Int) {
    Recommend("추천", 0), Search("검색", 1)
}

@Composable
private fun TouristAddContent(
    keyword: String,
    onValueChange: (String) -> Unit,
    selectedArrange: String,
    selectedContentType: String,
    tab: TravelAddTabs,
    onArrangeClick: () -> Unit,
    onContentTypeClick: () -> Unit
) {
    when (tab) {
        Recommend -> {
            RecommendFilter(
                selectedSortType = selectedArrange,
                selectedTouristType = selectedContentType,
                onArrangeClick = onArrangeClick,
                onContentTypeClick = onContentTypeClick,
            )
        }

        Search -> {
            CustomSearchView(
                value = keyword,
                onValueChange = onValueChange,
                onValueClear = { onValueChange("") },
            )
        }
    }
}

