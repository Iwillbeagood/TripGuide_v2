package com.jun.tripguide_v2.feature.travelInit.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.component.CustomGifImage
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.MeansItem
import com.jun.tripguide_v2.core.model.MeansType

@Composable
fun TravelMeans(
    meansItems: List<MeansItem>,
    onItemClick: (MeansType) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(meansItems) { item ->
            MeansItem(
                isSelected = item.isSelected,
                id = if (item.isSelected) item.selectedIcon else item.icon,
                text = item.type.value,
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
    isSelected: Boolean,
    @DrawableRes id: Int,
    text: String,
    modifier: Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) SkyGray else LightGray,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            if (isSelected) {
                CustomGifImage(
                    gifImage = id,
                    modifier = Modifier.size(90.dp)
                )
            } else {
                Image(
                    painter = painterResource(id),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Gray),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(90.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Sky else Gray
            )
        }
    }
}