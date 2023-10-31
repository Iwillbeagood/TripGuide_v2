package com.jun.tripguide_v2.feature.addtravel

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import com.jun.tripguide_v2.core.designsystem.theme.TravelGuideTheme
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.MeansItems
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.travel_init.R

@Composable
fun TravelMeans(
    meansItems: List<MeansItems>,
    onItemClick: (MeansType) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(meansItems) { item ->
            MeansItem(
                drawable = item.drawable,
                text = item.type.value,
                color = if (item.isSelected) SkyGray else White,
                textColor = if (item.isSelected) Sky else Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item.type)
                    }
            )
        }
    }
}

@Composable
internal fun MeansItem(
    @DrawableRes drawable: Int,
    text: String,
    color: Color,
    textColor: Color,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(2.dp, Gray),
        color = color,
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(130.dp)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = textColor),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 5.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = textColor
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