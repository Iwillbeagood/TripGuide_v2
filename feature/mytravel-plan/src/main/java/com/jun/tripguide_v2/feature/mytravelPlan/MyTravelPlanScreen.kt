/**
 * https://github.com/aclassen/ComposeReorderable
 * */
package com.jun.tripguide_v2.feature.mytravelPlan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomLoading
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.feature.mytravelPlan.component.RouteItem
import com.jun.tripguide_v2.feature.mytravelPlan.util.startKakaoNavigation
import com.jun.tripguide_v2.feature.mytravelPlan.util.toYearStringType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyTravelPlanRoute(
    travelId: String,
    onBackClick: () -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: MyTravelPlanViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    LaunchedEffect(true) {
        viewModel.getTravelRoute(travelId)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        MyTravelPlanContent(
            uiState = uiState,
            onBackClick = onBackClick,
            onNavigateToRoute = {
                startKakaoNavigation(context, it.title, it.mapX, it.mapY)
            }
        )
    }
}

@Composable
fun MyTravelPlanContent(
    uiState: MyTravelPlanUiState,
    onBackClick: () -> Unit,
    onNavigateToRoute: (Tourist) -> Unit
) {
    when (uiState) {
        MyTravelPlanUiState.Loading -> CustomLoading()
        is MyTravelPlanUiState.Routes -> MyTravelPlanScreen(
            dateString = DateDuration(uiState.travel.startDate, uiState.travel.endDate).toYearStringType(),
            routes = uiState.routes,
            onBackClick = onBackClick,
            onNavigateToRoute = onNavigateToRoute,
        )
    }
}

@Composable
fun MyTravelPlanScreen(
    dateString: String,
    routes: List<Tourist>,
    onBackClick: () -> Unit,
    onNavigateToRoute: (Tourist) -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "여행 일정",
                navigationType = TopAppBarNavigationType.Back,
                onNavigationClick = onBackClick,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = dateString,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            TravelRouteColumn(
                routes = routes,
                onNavigateToRoute = onNavigateToRoute,
            )
        }
    }
}

@Composable
fun TravelRouteColumn(
    routes: List<Tourist>,
    onNavigateToRoute: (Tourist) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        items(routes, { it.id }) { route ->
            RouteItem(
                title = route.title,
                type = ContentType.findByType(route.type),
                imageUrl = route.firstImage,
                onNavigateToRoute = { onNavigateToRoute(route) },
            )
        }
    }
}