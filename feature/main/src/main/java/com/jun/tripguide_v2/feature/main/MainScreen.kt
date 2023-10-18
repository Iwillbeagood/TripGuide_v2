package com.jun.tripguide_v2.feature.main

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import com.jun.tripguide_v2.core.designsystem.R
import com.jun.tripguide_v2.feature.addtravel.navigation.addTravelNavGraph
import com.jun.tripguide_v2.feature.mytravel.navigation.myTravelNavGraph
import com.jun.tripguide_v2.feature.recommend.navigation.recommendNavGraph
import com.jun.tripguide_v2.feature.setting.navigation.settingNavGraph
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                bottomItems = MainBottomNavItem.values().toList().toPersistentList(),
                currentItem = navigator.currentItem,
                onBottomItemClicked = { navigator.navigate(it) }
            )
        },
        floatingActionButton = {
            ItemAddFAB(
                visible = navigator.shouldShowBottomBar(),
                onClicked = {
                    navigator.navigateAddTravel()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavigationGraph(
                navigator = navigator
            )
        }
    }
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
private fun NavigationGraph(
    navigator: MainNavigator
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination
    ) {
        myTravelNavGraph(

        )
        recommendNavGraph(

        )
        settingNavGraph(

        )
        addTravelNavGraph(
            onBackClick = navigator::popBackStackIfNotHome,
            onBackClickAreaCodes = { defaultAreaCode, areaCode ->
                navigator.popBackStackWithData("destination", "${defaultAreaCode.name} ${areaCode.name}")
            },
            onBackClickAddress = { address ->
                navigator.popBackStackWithData("address", address.name)
            },
            onPickTravelInfoClick = navigator::navigatePickDestination,
            onPickStartingPointClick = navigator::navigatePickStartingPoint,
        )
    }
}

@Composable
fun ItemAddFAB(
    visible: Boolean,
    onClicked: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier
            .padding(5.dp)
            .alpha(if (visible) 1f else 0f),
        onClick = onClicked,
        shape = CircleShape,
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}