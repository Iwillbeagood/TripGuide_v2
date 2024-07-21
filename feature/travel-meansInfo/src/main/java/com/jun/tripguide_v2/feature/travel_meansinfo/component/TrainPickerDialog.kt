package com.jun.tripguide_v2.feature.travel_meansinfo.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowRightAlt
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.White
import com.jun.tripguide_v2.core.model.TrainInfo
import com.jun.tripguide_v2.core.model.TrainStation
import com.jun.tripguide_v2.core.model.TrainType
import com.jun.tripguide_v2.feature.travel_meansinfo.R

@Composable
fun TrainStationPickerDialog(
    onDismissRequest: () -> Unit,
    list: List<TrainStation>,
    onClick: (TrainStation) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceDim)
                .clip(RoundedCornerShape(5.dp))
        ) {
            LazyColumn {
                items(list) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(vertical = 10.dp)
                            .clickable {
                                onClick(item)
                                onDismissRequest()
                            }
                    ) {
                        Text(
                            text = item.stationName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .align(Alignment.CenterStart)
                        )
                        RadioButton(
                            selected = item.isSelected, onClick = {
                                onClick(item)
                                onDismissRequest()
                            },
                            modifier = Modifier.align(
                                Alignment.CenterEnd
                            )
                        )
                    }
                    Divider(color = Gray)
                }
            }
        }
    }
}

@Composable
fun TrainInfoPickerDialog(
    onDismissRequest: () -> Unit,
    list: List<TrainInfo>,
    onClick: (List<TrainInfo>) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceDim)
        ) {
            LazyColumn {
                items(list) { item ->
                    TrainInfoItem(
                        trainInfo = item,
                        modifier = Modifier
                            .clickable {
                                onClick(list.map {
                                    it.copy(
                                        isSelected = it == item
                                    )
                                })
                                onDismissRequest()
                            }
                    )
                    Divider(color = Gray)
                }
            }
        }
    }
}

@Composable
private fun TrainInfoItem(
    modifier: Modifier = Modifier,
    trainInfo: TrainInfo
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when (trainInfo.trainType) {
                is TrainType.Etc -> Spacer(modifier = Modifier.height(30.dp))
                TrainType.Ktx -> Image(
                    painter = painterResource(id = R.drawable.ktx),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 30.dp, width = 100.dp)
                        .padding(end = 10.dp)
                )
                TrainType.KtxSancheon -> Image(
                    painter = painterResource(id = R.drawable.ktx_sancheon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 30.dp, width = 100.dp)
                        .padding(end = 10.dp)
                )
            }
            Text(
                text = trainInfo.trainNo.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = trainInfo.depPlanedTime,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = trainInfo.depPlaceName,
                    fontSize = 18.sp
                )
            }
            Icon(
                imageVector = Icons.Sharp.ArrowRightAlt,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
            Column(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = trainInfo.arrPlanedTime,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = trainInfo.arrPlaceName,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrainInfoItemPreview() {
    TrainInfoItem(
        trainInfo = TrainInfo(
            trainType = TrainType.KtxSancheon,
            trainNo = 123,
            depPlanedTime = "06:05",
            depPlaceName = "서울",
            arrPlanedTime = "07:50",
            arrPlaceName = "광명"
        )
    )
}

@Preview
@Composable
private fun TrainStationPickerDialogPreview() {
    TrainStationPickerDialog(
        {},
        listOf(
            TrainStation("", "광명"),
            TrainStation("", "서울"),
            TrainStation("", "인천공항"),
        )
    ) {
    }
}