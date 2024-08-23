package com.jun.tripguide_v2.feature.mytravelPlan.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.component.CustomImage
import com.jun.tripguide_v2.core.designsystem.theme.DuskGray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.feature.mytravel_plan.R

@Composable
fun RouteItem(
    title: String,
    type: ContentType,
    imageUrl: String,
    onNavigateToRoute: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 10.dp)
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
    }
}