package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.jun.tripguide_v2.core.model.ContentType
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun CustomImage(
    imageUrl: String,
    type: ContentType,
    modifier: Modifier,
    failure: @Composable BoxScope.() -> Unit = {
        CustomImage(imageUrl = getUrlByType(type), type = ContentType.All, modifier = modifier)
    }
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
fun BoxScope.FailureImage(
    type: ContentType
) {

}

fun getUrlByType(type: ContentType) = when (type) {
    ContentType.All -> "https://i.ibb.co/0qq6knF/all.jpg"
    ContentType.TouristSpot -> "https://i.ibb.co/fxFZ9Zq/Tourist-Spot.jpg"
    ContentType.CulturalFacility -> "https://i.ibb.co/1XMXdsb/Cultural-Facility.jpg"
    ContentType.EventFestival -> "https://i.ibb.co/8sBKcYy/Event-Festival.jpg"
    ContentType.TravelCourse -> "https://i.ibb.co/6ZzJ17J/Travel-Course.jpg"
    ContentType.Recreation -> "https://i.ibb.co/Jyfgymc/Recreation.jpg"
    ContentType.Accommodation -> "https://i.ibb.co/8Nj2t1L/Accommodation.jpg"
    ContentType.Shopping -> "https://i.ibb.co/RDWcp74/Shopping.jpg"
    ContentType.Restaurant -> "https://i.ibb.co/bdPSHGz/Restaurant.jpg"
}