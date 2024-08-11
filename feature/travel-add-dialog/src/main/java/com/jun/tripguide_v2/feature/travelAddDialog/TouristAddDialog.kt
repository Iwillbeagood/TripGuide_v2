package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.SelectedTourist
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.component.TouristLazyColumn
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun TouristAddDialog(
    destinationCode: DestinationCode,
    onDismissRequest: () -> Unit,
    onTouristAddComplete: (List<Tourist>) -> Unit,
    onTouristDetail: (id: String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: TouristAddViewModel = hiltViewModel()
) {
    val tourists by viewModel.touristsState.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val selectedTourists by viewModel.selectedTourists.collectAsStateWithLifecycle()

    LaunchedEffect(destinationCode) {
        viewModel.initViewModel(destinationCode)
    }

    LaunchedEffect(true) {
        viewModel.touristAddEffect.collectLatest {
            when (it) {
                is TravelAddDialogUiEffect.ShowErrorSnackBar -> onShowErrorSnackBar(it.throwable)
                is TravelAddDialogUiEffect.TravelRecommendComplete -> {
                    onTouristAddComplete(it.tourists)
                }
            }
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        TouristScreen(
            filterState = filterState,
            tourists = tourists.tourists,
            selectedTourists = selectedTourists,
            onTouristDetail = { onTouristDetail(it.id) },
            onSelectTourist = viewModel::selectedTourist,
            onUnselectTourist = viewModel::unselectedTourist,
            onBackClick = onDismissRequest,
            travelRecommendComplete = {
                viewModel.touristAddComplete()
                onDismissRequest()
            },
            onArrangeClick = viewModel::changeArrange,
            onContentTypeClick = viewModel::changeContentType
        )
    }
}

@Composable
fun TouristScreen(
    filterState: FilterState,
    listState: LazyListState = rememberLazyListState(),
    tourists: List<Tourist>,
    selectedTourists: List<Tourist>,
    onTouristDetail: (Tourist) -> Unit,
    onSelectTourist: (Tourist) -> Unit,
    onUnselectTourist: (Tourist) -> Unit,
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
                onClickSelectedTourist = onUnselectTourist
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
                RecommendFilter(
                    selectedSortType = filterState.selectedArrange.title,
                    selectedTouristType = filterState.selectedContentType.title,
                    onArrangeClick = {
                        showArrange = true
                    },
                    onContentTypeClick = {
                        showContentType = true
                    },
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