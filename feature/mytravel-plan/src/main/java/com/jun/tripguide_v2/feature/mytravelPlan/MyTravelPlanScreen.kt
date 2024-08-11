/**
 * https://github.com/aclassen/ComposeReorderable
 * */
package com.jun.tripguide_v2.feature.mytravelPlan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomAlertDialog
import com.jun.tripguide_v2.core.designsystem.component.CustomGifImage
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.designsystem.theme.DuskGray
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.PaleGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.core.model.TravelDay
import com.jun.tripguide_v2.feature.mytravelPlan.component.RouteItem
import com.jun.tripguide_v2.feature.mytravelPlan.component.RouteMarkerItem
import com.jun.tripguide_v2.feature.mytravelPlan.component.RoutesMap
import com.jun.tripguide_v2.feature.mytravelPlan.util.startKakaoNavigation
import com.jun.tripguide_v2.feature.mytravel_plan.R
import kotlinx.coroutines.flow.collectLatest
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import java.time.Duration

@Composable
fun MyTravelPlanRoute(
    travelId: String,
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onSearchRoute: (String) -> Unit,
    onRecommendRoute: (String) -> Unit = {},
    viewModel: MyTravelPlanViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsStateWithLifecycle()

    val stateRouteMarkerColumn = rememberLazyListState()
    val stateRouteColumn = rememberReorderableLazyListState(
        onMove = viewModel::onMoveRouteItem,
        canDragOver = viewModel::isDragEnabled
    )

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    LaunchedEffect(true) {
        viewModel.fetchOrderedTravelRoute(travelId)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        MyTravelPlanContent(
            uiState = uiState,
            routesMapVisible = uiEffect is MyTravelPlanUiEffect.ShowRoutesMap,
            stateRouteColumn = stateRouteColumn,
            stateMarkerColumn = stateRouteMarkerColumn,
            viewModel = viewModel,
            onBackClick = {
                viewModel.showEditConfirmationDialog()
                onBackClick()
            },
            onSearchRoute = onSearchRoute,
            onRecommendRoute = onRecommendRoute,
            onNavigateToRoute = {
                startKakaoNavigation(context, it.title, it.mapX, it.mapY)
            }
        )
        if (uiState is MyTravelPlanUiState.Success) {
            RoutesMapButton(
                onClicked = viewModel::changeRoutesMapState,
                routesMapVisible = uiEffect is MyTravelPlanUiEffect.ShowRoutesMap
            )
        }
    }

    LaunchedEffect(stateRouteColumn.listState.firstVisibleItemScrollOffset) {
        stateRouteMarkerColumn.scrollToItem(
            stateRouteColumn.listState.firstVisibleItemIndex,
            stateRouteColumn.listState.firstVisibleItemScrollOffset
        )
    }

    LaunchedEffect(stateRouteMarkerColumn.firstVisibleItemScrollOffset) {
        stateRouteColumn.listState.scrollToItem(
            stateRouteMarkerColumn.firstVisibleItemIndex,
            stateRouteMarkerColumn.firstVisibleItemScrollOffset
        )
    }

    if (uiEffect is MyTravelPlanUiEffect.ShowEditConfirmationDialog) {
        CustomAlertDialog(
            onDismissRequest = viewModel::editDialogDismiss,
            onConfirmation = viewModel::editDialogConfirmation,
            dialogTitle = "편집 모드 종료",
            dialogText = "변경한 여행 일정을 반영하시겠습니까?"
        )
    }
}

@Composable
fun MyTravelPlanContent(
    uiState: MyTravelPlanUiState,
    routesMapVisible: Boolean,
    stateRouteColumn: ReorderableLazyListState,
    stateMarkerColumn: LazyListState,
    viewModel: MyTravelPlanViewModel,
    onBackClick: () -> Unit,
    onSearchRoute: (String) -> Unit,
    onRecommendRoute: (String) -> Unit,
    onNavigateToRoute: (Route) -> Unit
) {
    when (uiState) {
        is MyTravelPlanUiState.Loading -> MyTravelPlanLoadingScreen()
        is MyTravelPlanUiState.Success -> {
            MyTravelPlanScreen(
                routesMapVisible = routesMapVisible,
                stateMarkerColumn = stateMarkerColumn,
                stateRouteColumn = stateRouteColumn,
                isEditMode = uiState.isEditMode,
                title = uiState.travel.destination.destinationString,
                travelDays = uiState.travelDays,
                dayRoutes = uiState.dayRoutes,
                selectedRoute = uiState.selectedRoute,
                duration = uiState.duration,
                onBackClick = onBackClick,
                onTravelDayItemClick = { viewModel.travelDaysItemPicked(it.day) },
                onRouteItemClick = { viewModel.selectRouteItem(it.orderNum) },
                onEditMode = {
                    if (uiState.isEditMode) {
                        viewModel.showEditConfirmationDialog()
                    }
                    viewModel.changeEditMode()
                },
                onSearchRoute = {
                    onSearchRoute("${uiState.travel.travelId}|${it.orderNum}")
                },
                onRecommendRoute = {
                    onRecommendRoute("${uiState.travel.travelId}|${it.orderNum}")
                },
                onDeleteRoute = viewModel::deleteRoute,
                onNavigateToRoute = onNavigateToRoute
            )
        }
    }
}

@Composable
fun MyTravelPlanLoadingScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        CustomGifImage(
            gifImage = R.drawable.gif_loading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp, end = 100.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "여행 일정을 만들고 있습니다.",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "효율적인 경로 탐색을 위해\n1 ~ 2분의 시간이 걸릴 수 있습니다.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = DuskGray
        )
    }
}

@Composable
fun MyTravelPlanScreen(
    routesMapVisible: Boolean,
    stateMarkerColumn: LazyListState,
    stateRouteColumn: ReorderableLazyListState,
    isEditMode: Boolean,
    title: String,
    travelDays: List<TravelDay>,
    dayRoutes: List<Route>,
    selectedRoute: Route,
    duration: Duration,
    onBackClick: () -> Unit,
    onRouteItemClick: (Route) -> Unit,
    onTravelDayItemClick: (TravelDay) -> Unit,
    onEditMode: () -> Unit,
    onSearchRoute: (Route) -> Unit,
    onRecommendRoute: (Route) -> Unit,
    onDeleteRoute: (Route) -> Unit,
    onNavigateToRoute: (Route) -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = if (!isEditMode) title else "편집 모드",
                navigationType = TopAppBarNavigationType.Back,
                onNavigationClick = onBackClick,
                actionButtons = {
                    IconButton(
                        onClick = onEditMode,
                        modifier = Modifier.size(55.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.EditNote,
                            contentDescription = null,
                        )
                    }
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
            Spacer(modifier = Modifier.height(14.dp))
            TravelDaysRow(
                travelDays = travelDays,
                onTravelDayItemClick = onTravelDayItemClick
            )
            AnimatedVisibility(routesMapVisible) {
                RoutesMap(routes = dayRoutes, selectedRoute = selectedRoute)
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                AnimatedVisibility(
                    visible = !isEditMode,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally()
                ) {
                    RouteMarkerColumn(
                        state = stateMarkerColumn,
                        routes = dayRoutes,
                        duration = duration,
                    )
                }
                TravelRouteColumn(
                    state = stateRouteColumn,
                    dayRoutes = dayRoutes,
                    isEditMode = isEditMode,
                    onNavigateToRoute = onNavigateToRoute,
                    onRouteItemClick = onRouteItemClick,
                    onSearchRoute = onSearchRoute,
                    onRecommendRoute = onRecommendRoute,
                    onDeleteRoute = onDeleteRoute
                )
            }
        }
    }
}

@Composable
fun TravelRouteColumn(
    state: ReorderableLazyListState,
    dayRoutes: List<Route>,
    isEditMode: Boolean = true,
    onNavigateToRoute: (Route) -> Unit,
    onRouteItemClick: (Route) -> Unit,
    onSearchRoute: (Route) -> Unit,
    onRecommendRoute: (Route) -> Unit,
    onDeleteRoute: (Route) -> Unit
) {
    LazyColumn(
        state = state.listState,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp, bottom = 10.dp)
            .reorderable(state)
    ) {
        items(dayRoutes, { it.orderNum }) { route ->
            ReorderableItem(state, key = route.orderNum) { isDragging ->
                val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp, label = "")
                if (isEditMode) {
                    if (route.isLast) {
                        RouteItem(
                            elevation = elevation.value,
                            isFirst = route.isFirst,
                            isLast = true,
                            isEditMode = true,
                            selected = route.isSelected,
                            title = route.title,
                            type = ContentType.findByType(route.type),
                            imageUrl = route.firstImage,
                            startTime = route.time,
                            modifier = Modifier
                                .fillMaxSize(),
                        )
                    } else if (route.isFirst) {
                        RouteItem(
                            elevation = elevation.value,
                            isFirst = true,
                            isEditMode = true,
                            selected = route.isSelected,
                            title = route.title,
                            type = ContentType.findByType(route.type),
                            imageUrl = route.firstImage,
                            startTime = route.time,
                            onDeleteRoute = { onDeleteRoute(route) },
                            onSearchRoute = { onSearchRoute(route) },
                            onRecommendRoute = { onRecommendRoute(route) },
                            modifier = Modifier
                                .fillMaxSize()
                                .detectReorderAfterLongPress(state)
                                .clickable {
                                    onRouteItemClick(route)
                                }
                        )
                    } else {
                        RouteItem(
                            elevation = elevation.value,
                            isEditMode = true,
                            selected = route.isSelected,
                            title = route.title,
                            type = ContentType.findByType(route.type),
                            imageUrl = route.firstImage,
                            startTime = route.time,
                            onDeleteRoute = { onDeleteRoute(route) },
                            onSearchRoute = { onSearchRoute(route) },
                            onRecommendRoute = { onRecommendRoute(route) },
                            modifier = Modifier
                                .fillMaxSize()
                                .detectReorderAfterLongPress(state)
                                .clickable {
                                    onRouteItemClick(route)
                                }
                        )
                    }
                } else {
                    RouteItem(
                        elevation = elevation.value,
                        selected = route.isSelected,
                        title = route.title,
                        type = ContentType.findByType(route.type),
                        imageUrl = route.firstImage,
                        onNavigateToRoute = { onNavigateToRoute(route) },
                        startTime = route.time,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onRouteItemClick(route)
                            }
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RouteMarkerColumn(
    state: LazyListState,
    routes: List<Route>,
    duration: Duration
) {
    LazyColumn(
        state = state,
        modifier = Modifier.padding(5.dp)
    ) {
        items(routes) { route ->
            RouteMarkerItem(
                isFirst = route == routes.first(),
                isEnd = route == routes.last(),
                isSelected = route.isSelected,
                isBeforeItemSelected = route.isBeforeRouteSelected,
                duration = duration,
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
fun TravelDaysRow(
    travelDays: List<TravelDay>,
    onTravelDayItemClick: (TravelDay) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        items(travelDays) { item ->
            DayItem(
                day = item.day,
                detailDay = item.detailsDay,
                color = if (item.isSelected) Sky else LightGray,
                dayTextColor = if (item.isSelected) PaleGray else Black,
                detailDayTextColor = if (item.isSelected) LightGray else DuskGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onTravelDayItemClick(item)
                    }
            )
        }
    }
}

@Composable
internal fun DayItem(
    day: Int,
    detailDay: String,
    color: Color,
    dayTextColor: Color,
    detailDayTextColor: Color,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = color,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(100.dp)
                .padding(7.dp)
        ) {
            Text(
                text = detailDay,
                style = MaterialTheme.typography.titleMedium,
                color = detailDayTextColor
            )
            Text(
                text = "Day $day",
                style = MaterialTheme.typography.headlineSmall,
                color = dayTextColor
            )
        }
    }
}

@Composable
fun RoutesMapButton(
    onClicked: () -> Unit,
    routesMapVisible: Boolean
) {
    Button(
        shape = CircleShape,
        onClick = onClicked,
        contentPadding = PaddingValues(0.dp),
        colors = if (routesMapVisible) ButtonDefaults.buttonColors(Sky.copy(alpha = 0.8f)) else ButtonDefaults.buttonColors(
            Gray.copy(alpha = 0.8f)
        ),
        modifier = Modifier
            .wrapContentSize(Alignment.BottomEnd)
            .padding(10.dp)
            .size(60.dp)
    ) {
        Icon(Icons.Outlined.Map, null)
    }
}