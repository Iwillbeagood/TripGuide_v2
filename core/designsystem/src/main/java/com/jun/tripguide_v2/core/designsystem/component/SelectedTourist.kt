@file:OptIn(ExperimentalFoundationApi::class)

package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.Tourist
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun SelectedTourist(
    visible: Boolean,
    selectedTouristList: List<Tourist>,
    onClickSelectedTourist: (Tourist) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, LightGray),
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, top = 14.dp),
        ) {
            LazyRow {
                items(
                    items = selectedTouristList,
                    key = { item -> item.id }
                ) { tourist ->
                    SelectedTouristItem(
                        title = tourist.title,
                        imageUrl = tourist.firstImage,
                        modifier = Modifier
                            .animateItemPlacement()
                            .clickable {
                                onClickSelectedTourist(tourist)
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedTouristItem(
    title: String,
    imageUrl: String,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = White,
        modifier = modifier.padding(10.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImage(
                imageModel = { imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                ),
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                color = Color.Black,
                maxLines = 1,
                modifier = Modifier.width(60.dp)
            )
        }
    }
}
