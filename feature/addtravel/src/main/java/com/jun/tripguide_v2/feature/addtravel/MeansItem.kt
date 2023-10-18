package com.jun.tripguide_v2.feature.addtravel

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.TravelGuideTheme
import com.jun.tripguide_v2.core.model.MeansItems
import com.jun.tripguide_v2.core.model.MeansType

@Composable
internal fun MeansItem(
    @DrawableRes drawable: Int,
    text: String,
    color: Color,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = color,
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(130.dp)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 5.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun MeansItemPreview() {
    TravelGuideTheme {
        MeansItems(
            drawable = R.drawable.ic_car,
            type = MeansType.CAR
        )
    }
}