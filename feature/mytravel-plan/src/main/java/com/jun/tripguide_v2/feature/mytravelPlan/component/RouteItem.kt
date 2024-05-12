package com.jun.tripguide_v2.feature.mytravelPlan.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Festival
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Tour
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.theme.DuskGray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.feature.mytravel.plan.R
import com.jun.tripguide_v2.feature.mytravelPlan.util.dateTimeFormatter
import java.time.LocalTime

@Composable
fun RouteItem(
    elevation: Dp,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    isEditMode: Boolean = false,
    selected: Boolean,
    title: String,
    type: ContentType,
    imageUrl: String,
    startTime: LocalTime,
    modifier: Modifier,
    onNavigateToRoute: () -> Unit = {},
    onDeleteRoute: () -> Unit = {},
    onSearchRoute: () -> Unit = {},
    onRecommendRoute: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(elevation)
            .padding(start = if (isEditMode) 10.dp else 0.dp, end = if (isEditMode) 0.dp else 10.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Surface(
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(1.dp, LightGray),
                color = White,
            ) {
                Column {
                    Surface(
                        color = White,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top
                        ) {
                            Surface(
                                shape = RoundedCornerShape(2.dp),
                                modifier = Modifier.padding(5.dp),
                            ) {
                                CustomImage(
                                    imageUrl = imageUrl,
                                    type = type,
                                    modifier = Modifier.size(60.dp),
                                )
                            }

                            Column(
                                modifier = Modifier.padding(start = 5.dp, top = 10.dp)
                            ) {
                                Text(
                                    text = if (type.filterValue.title != "전체") type.filterValue.title else "출발지",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = DuskGray
                                )
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                )
                            }
                        }

                        if ((startTime.hour != 0 || startTime.minute != 0) && !isEditMode) {
                            Text(
                                text = startTime.dateTimeFormatter(),
                                fontSize = 12.sp,
                                color = DuskGray,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .wrapContentSize(Alignment.TopEnd)
                            )
                        }
                    }
                    AnimatedVisibility(
                        visible = selected && !isEditMode,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Surface(
                            color = White,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.kakaonavi),
                                contentDescription = null,
                                modifier = Modifier
                                    .wrapContentSize(Alignment.BottomEnd)
                                    .size(60.dp)
                                    .padding(8.dp)
                                    .clickable {
                                        onNavigateToRoute()
                                    }
                            )
                        }
                    }
                }
            }
            if (isEditMode && selected) {
                RouteAddItem(
                    onSearchRoute = onSearchRoute,
                    onRecommendRoute = onRecommendRoute
                )
            }
        }
        if (isEditMode) {
            IconButton(onClick = {
                if (!(isFirst || isLast)) {
                    onDeleteRoute()
                }
            }) {
                Icon(
                    imageVector = if (isFirst || isLast) Icons.Default.Remove else Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .weight(0.2f)
                )
            }
        }
    }
}

@Composable
fun RouteAddItem(
    onSearchRoute: () -> Unit,
    onRecommendRoute: () -> Unit,
) {
    Row(
        Modifier.padding(top = 10.dp, bottom = 5.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, LightGray),
            color = White,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onSearchRoute()
                }
        ) {
            Row(
                Modifier.wrapContentSize(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(10.dp)
                )
                Text(
                    text = "검색 장소 추가",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Surface(
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, LightGray),
            color = White,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onRecommendRoute()
                }
        ) {
            Row(
                Modifier.wrapContentSize(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(10.dp)
                )
                Text(
                    text = "추천 장소 추가",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                )
            }
        }
    }
}

private fun getImageOfType(contentType: ContentType): ImageVector {
    return when (contentType) {
        ContentType.All -> Icons.Default.Home
        ContentType.TouristSpot -> Icons.Default.Tour
        ContentType.CulturalFacility -> Icons.Default.SportsGymnastics
        ContentType.EventFestival -> Icons.Default.Festival
        ContentType.TravelCourse -> Icons.Default.DirectionsRun
        ContentType.Recreation -> Icons.Default.Casino
        ContentType.Accommodation -> Icons.Default.Hotel
        ContentType.Shopping -> Icons.Default.ShoppingCart
        ContentType.Restaurant -> Icons.Default.Restaurant
    }
}