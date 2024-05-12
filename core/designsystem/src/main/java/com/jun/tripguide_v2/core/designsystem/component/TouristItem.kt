package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.ContentType

@Composable
fun TouristItem(
    title: String,
    type: ContentType,
    address: String,
    imageUrl: String,
    selected: Boolean,
    onSelectTourist: () -> Unit,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = if (selected) BorderStroke(3.dp, Sky.copy(alpha = 0.7f))
        else BorderStroke(1.dp,  LightGray),
        color = White,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp, top = 7.dp, bottom = 7.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Box {
                CustomImage(
                    imageUrl = imageUrl,
                    type = type,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
                Button(
                    shape = CircleShape,
                    onClick = onSelectTourist,
                    colors = if (selected) ButtonDefaults.buttonColors(Sky.copy(alpha = 0.7f))
                    else ButtonDefaults.buttonColors(Gray.copy(alpha = 0.7f)),
                    modifier = Modifier
                        .padding(5.dp)
                        .wrapContentSize(Alignment.TopEnd)
                ) {
                    Text(text = "선택")
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

@Preview
@Composable
private fun TouristItemPreview() {
    MyTheme {
        TouristItem(
            title = "언양전통불고기",
            type = ContentType.Restaurant,
            address = "울산광역시 울주운 언양읍 헌양길 128",
            imageUrl = "",
            selected = true,
            onSelectTourist = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                }
        )
    }
}