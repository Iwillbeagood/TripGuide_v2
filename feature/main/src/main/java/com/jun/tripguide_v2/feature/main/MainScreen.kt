package com.jun.tripguide_v2.feature.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import com.jun.tripguide_v2.core.designsystem.R
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import com.jun.tripguide_v2.feature.travelSearch.navigation.travelSearchNavGraph
import com.jun.tripguide_v2.feature.travelInit.navigation.travelInitNavGraph
import com.jun.tripguide_v2.feature.main.R.string
import com.jun.tripguide_v2.feature.mytravel.navigation.myTravelNavGraph
import com.jun.tripguide_v2.feature.mytravelPlan.navigation.myTravelPlanNavGraph
import com.jun.tripguide_v2.feature.recommend.navigation.recommendNavGraph
import com.jun.tripguide_v2.feature.setting.navigation.settingNavGraph
import com.jun.tripguide_v2.feature.travelRecommend.navigation.travelRecommendNavGraph
import com.jun.tripguide_v2.tourist_detail.navigation.touristDetailNavGraph
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    var contentJob: Job? = null
    val coroutineScope = rememberCoroutineScope()
    val localContextResource = LocalContext.current.resources
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit = { throwable ->
        if (contentJob != null) {
            contentJob?.cancel()
        }
        contentJob = coroutineScope.launch {
            snackBarHostState.showSnackbar(
                when (throwable) {
                    is NullPointerException -> localContextResource.getString(string.error_message_null_pointer)
                    else -> localContextResource.getString(string.error_message_unknown)
                }
            )
        }
    }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination
                ) {
                    myTravelNavGraph(
                        onTravelClick = navigator::navigateTravelPlan,
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    myTravelPlanNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                        onSearchRoute = navigator::navigateTravelSearch,
                        onRecommendRoute = navigator::navigateTravelRecommend,
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    recommendNavGraph(
                        onTouristDetail = navigator::navigateTouristDetail,
                        goBack = { navigator.navigate(MainBottomNavItem.MY_TRAVEL) },
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    settingNavGraph(

                    )
                    travelInitNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                        onBackClickAreaCodes = { areaCode, sigunguCode ->
                            val title = if (areaCode != sigunguCode) {
                                "${areaCode.name} ${sigunguCode.name}"
                            } else {
                                areaCode.name
                            }
                            navigator.popBackStackWithData(
                                "destination",
                                "${areaCode.code}/${sigunguCode.code}/$title"
                            )
                        },
                        onBackClickAddress = { address ->
                            navigator.popBackStackWithData(
                                "startingPoint",
                                "${address.name}/${address.address}/${address.x}/${address.y}"
                            )
                        },
                        onPickTravelInfoClick = navigator::navigatePickDestination,
                        onPickStartingPointClick = navigator::navigatePickStartingPoint,
                        onTravelInitComplete = navigator::navigateTravelRecommend,
                        onShowErrorSnackBar = onShowErrorSnackBar
                    )
                    travelRecommendNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                        onShowErrorSnackBar = onShowErrorSnackBar,
                        onTravelRouteComplete = navigator::navigateTravelSearch,
                        onTouristDetail = navigator::navigateTouristDetail,
                    )
                    travelSearchNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                        onShowErrorSnackBar = onShowErrorSnackBar,
                        onTravelSearchComplete = navigator::popBackUntilStart,
                        onTouristDetail = navigator::navigateTouristDetail,
                    )
                    touristDetailNavGraph(
                        onBackClick = navigator::popBackStackIfNotHome,
                    )
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                bottomItems = MainBottomNavItem.values().toList().toPersistentList(),
                currentItem = navigator.currentItem,
                onBottomItemClicked = navigator::navigate
            )
        },
        floatingActionButton = {
            ItemAddFAB(
                visible = navigator.shouldShowBottomBar(),
                icon = Icons.Filled.Add,
                onClicked = navigator::navigateTravelInit
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    )
}

@Composable
private fun MainBottomBar(
    visible: Boolean,
    bottomItems: PersistentList<MainBottomNavItem>,
    currentItem: MainBottomNavItem?,
    onBottomItemClicked: (MainBottomNavItem) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        NavigationBar(
            containerColor = Color.White
        ) {
            bottomItems.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier
                                .width(26.dp)
                                .height(26.dp),
                            tint = colorResource(id = R.color.sky)
                        )
                    },
                    label = { Text(text = item.title, fontSize = 9.sp) },
                    onClick = { onBottomItemClicked(item) },
                    selected = item == currentItem
                )
            }
        }
    }
}

@Composable
fun ItemAddFAB(
    visible: Boolean,
    icon: ImageVector,
    onClicked: () -> Unit
) {
    if (!visible) return
    ExtendedFloatingActionButton(
        onClick = onClicked,
        shape = CircleShape,
        containerColor = SkyGray,
        contentColor = Color.Black,
        modifier = Modifier.padding(1.dp)
    ) {
        Icon(icon, "Floating action button.")
        Text(text = "일정 추가")
    }
}