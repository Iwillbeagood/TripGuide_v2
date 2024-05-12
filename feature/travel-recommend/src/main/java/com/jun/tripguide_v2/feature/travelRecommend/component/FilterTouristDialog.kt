package com.jun.tripguide_v2.feature.travelRecommend.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jun.tripguide_v2.core.designsystem.theme.DuskGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.FilterValue

@Composable
fun FilterTouristDialog(
    visible: Boolean,
    sortByList: List<FilterValue>,
    typeByList: List<FilterValue>,
    sortByItemClick: (FilterValue) -> Unit,
    typeByItemClick: (FilterValue) -> Unit,
    onBackClick: () -> Unit,
    onFilterByItemClick: () -> Unit,
) {

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally() + fadeIn(),
        exit = slideOutHorizontally() + fadeOut()
    ) {
        Dialog(
            onDismissRequest = onBackClick
        ) {
            FilterDialogContent(
                sortByList = sortByList,
                sortByItemClick = sortByItemClick,
                typeByList = typeByList,
                typeByItemClick = typeByItemClick,
                onBackClick = onBackClick,
                onFilterByItemClick = onFilterByItemClick
            )
        }
    }
}

@Composable
fun FilterDialogContent(
    sortByList: List<FilterValue>,
    typeByList: List<FilterValue>,
    sortByItemClick: (FilterValue) -> Unit,
    typeByItemClick: (FilterValue) -> Unit,
    onBackClick: () -> Unit,
    onFilterByItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        horizontalAlignment = Alignment.End
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Text(
                text = "정렬 방식 ->",
                fontWeight = FontWeight.Bold,
                color = DuskGray,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
                    .padding(horizontal = 15.dp)
            )
            FilterLazyRow(
                list = sortByList,
                onItemClick = {
                    Log.d("TAG", "FilterDialogContent: $it")
                    sortByItemClick(it)
                }
            )
            Divider(
                color = DuskGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
            )
            Text(
                text = "관광 타입 ->",
                fontWeight = FontWeight.Bold,
                color = DuskGray,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
                    .padding(horizontal = 15.dp)
            )
            FilterLazyRow(
                list = typeByList,
                onItemClick = typeByItemClick
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onBackClick) {
                    Text(
                        text = "취소",
                        fontWeight = FontWeight.Bold,
                        color = DuskGray
                    )
                }
                TextButton(onClick = onFilterByItemClick) {
                    Text(
                        text = "정렬",
                        fontWeight = FontWeight.Bold,
                        color = Sky
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun FilterLazyRow(
    list: List<FilterValue>,
    onItemClick: (FilterValue) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(list) { item ->
            FilterItem(
                text = item.title,
                color = if (item.isSelected) SkyGray else White,
                textColor = if (item.isSelected) Sky else DuskGray,
                onItemClick = {
                    onItemClick(item)
                }
            )
        }
    }
}

@Composable
fun FilterItem(
    text: String,
    color: Color,
    textColor: Color,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            shape = CircleShape,
            border = BorderStroke(2.dp, DuskGray),
            onClick = onItemClick,
            colors = ButtonDefaults.buttonColors(color),
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

