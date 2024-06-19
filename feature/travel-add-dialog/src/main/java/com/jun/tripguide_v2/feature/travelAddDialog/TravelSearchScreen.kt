package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.jun.tripguide_v2.core.designsystem.component.SelectedTourist
import com.jun.tripguide_v2.core.designsystem.component.TouristLazyColumn
import com.jun.tripguide_v2.core.model.Tourist
import kotlinx.coroutines.launch

@Composable
fun TravelSearchScreen(
    touristList: List<Tourist>,
    selectedTouristList: List<Tourist>,
    onSelectTourist: (Tourist) -> Unit,
    onTouristDetail: (Tourist) -> Unit,
    fetchNextPageTourist: () -> Unit
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(listState.canScrollForward) {
        if (!listState.canScrollForward) {
            fetchNextPageTourist()
        }
    }

    SelectedTourist(
        visible = selectedTouristList.isNotEmpty(),
        selectedTouristList = selectedTouristList,
        onClickSelectedTourist = onSelectTourist
    )
    TouristLazyColumn(
        listState = listState,
        touristList = touristList,
        onClickTourist = onTouristDetail,
        scrollToFirstItem = {
            scope.launch {
                listState.scrollToItem(0)
            }
        },
        onSelectTourist = onSelectTourist,
    )
}