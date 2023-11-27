package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun CustomCoilImage(
    imageUrl: String,
    modifier: Modifier,
    failure: @Composable BoxScope.() -> Unit = { DefaultFailure() }
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        component = rememberImageComponent {
            +CrossfadePlugin(
                duration = 550
            )
        },
        modifier = modifier,
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        failure = {
            failure()
        }
    )
}

@Composable
fun BoxScope.DefaultFailure() {
    Box(modifier = Modifier.matchParentSize()) {
        Text(
            text = "이미지 로딩에 실패했습니다.",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}