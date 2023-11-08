package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun TouristItem(
    title: String,
    address: String,
    imageUrl: String,
    selected: Boolean,
    onSelectTourist: () -> Unit,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, LightGray),
        color = White,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp, top = 7.dp, bottom = 7.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Box {
                CoilImage(
                    imageModel = { imageUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                    component = rememberImageComponent {
                        + CrossfadePlugin(
                            duration = 550
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    loading = {
                        Box(modifier = Modifier.matchParentSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    failure = {
                        Text(text = "이미지 로딩에 실패했습니다.")
                    }
                )
                Button(
                    shape = CircleShape,
                    onClick = onSelectTourist,
                    colors = if (selected) ButtonDefaults.buttonColors(Sky.copy(alpha = 0.7f))
                    else ButtonDefaults.buttonColors(Gray.copy(alpha = 0.7f)),
                    modifier = Modifier
                        .padding(5.dp)
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    Text(text = if (selected) "Yes!" else "Traveling?")
                }
            }

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 15.dp, top = 15.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp, bottom = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "",
                    tint = Gray,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = address,
                    maxLines = 7,
                    style = MaterialTheme.typography.titleSmall,
                    color = Gray
                )
            }
        }
    }
}