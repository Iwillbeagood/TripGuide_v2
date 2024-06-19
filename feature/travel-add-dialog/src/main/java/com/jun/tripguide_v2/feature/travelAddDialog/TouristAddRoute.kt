package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.SelectedTourist
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.surfaceDim
import com.jun.tripguide_v2.core.model.Tourist
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TouristAddRoute(
    onBackClick: () -> Unit,
    onTravelRecommendComplete: (List<Tourist>) -> Unit,
    onTouristDetail: (id: String) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: TouristAddViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tab by viewModel.tab.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is TravelAddDialogUiEvent.ShowErrorSnackBar -> onShowErrorSnackBar(it.throwable)
                is TravelAddDialogUiEvent.TravelRecommendComplete -> {
                    onTravelRecommendComplete(it.tourists)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                CustomTopAppBar(
                    title = "여행 장소 추가",
                    navigationType = TopAppBarNavigationType.Back,
                    onNavigationClick = onBackClick,
                    actionButtons = {
                        IconButton(
                            onClick = viewModel::travelRecommendComplete,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                            )
                        }
                    }
                )
                TravelAddTab(
                    tab = tab,
                    onClick = viewModel::changeTab
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
                visible = uiState.selectedTourists.isNotEmpty(),
                selectedTouristList = uiState.selectedTourists.toList(),
                onClickSelectedTourist = viewModel::unSelectTourist
            )
            TravelRecommendContent(
                tabIndex = tab,
                onTouristDetail = { onTouristDetail(it.id) }
            )
        }
    }
}

enum class TravelAddTabs(val title: String, val index: Int) {
    Recommend("추천", 0), Search("검색", 1)
}

@Composable
private fun TravelAddTab(tab: TravelAddTabs, onClick: (TravelAddTabs) -> Unit) {

    TabRow(selectedTabIndex = tab.index) {
        TravelAddTabs.values().forEach {
            Tab(text = { Text(it.title) },
                selected = tab == it,
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}

@Composable
private fun TravelRecommendContent(
    tab: TravelAddTabs,
    viewModel: TouristAddViewModel,
    onTouristDetail: (Tourist) -> Unit,
) {
    when (tab) {
        TravelAddTabs.Recommend -> {
            TouristRecommendRoute(
                onTouristDetail = onTouristDetail,
                unSelectTourist = viewModel::unSelectTourist
            )
        }
        TravelAddTabs.Search -> TODO()
    }
}

