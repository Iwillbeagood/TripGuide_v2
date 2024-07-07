package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.LightGray
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.FilterValue

@Composable
fun RecommendFilter(
    selectedSortType: String,
    selectedTouristType: String,
    onArrangeClick: () -> Unit,
    onContentTypeClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        FilterButton(
            onFilterClick = onArrangeClick,
            selectedTypeString = selectedSortType
        )
        FilterButton(
            onFilterClick = onContentTypeClick,
            selectedTypeString = selectedTouristType
        )
    }
}

@Composable
private fun FilterButton(
    onFilterClick: () -> Unit,
    selectedTypeString: String
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(1.dp, LightGray),
        color = White,
        modifier = Modifier
            .clickable {
                onFilterClick()
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = selectedTypeString, modifier = Modifier.padding(14.dp))
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "ArrowDropDown")
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    filterList: List<FilterValue>,
    onFilterClick: (FilterValue) -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        filterList.forEach {
            Text(
                text = it.title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        onFilterClick(it)
                        onDismissRequest()
                    }
            )
        }
    }
}

@Preview
@Composable
private fun TravelRecommendScreenPreview() {
    MyTheme {
        RecommendFilter(
            selectedSortType = "type",
            selectedTouristType = "type",
            onArrangeClick = {},
            onContentTypeClick = {}
        )
    }
}