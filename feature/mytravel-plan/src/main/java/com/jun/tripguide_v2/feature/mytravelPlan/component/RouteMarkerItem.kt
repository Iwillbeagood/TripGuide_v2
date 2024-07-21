package com.jun.tripguide_v2.feature.mytravelPlan.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jun.tripguide_v2.core.designsystem.theme.DuskGray
import com.jun.tripguide_v2.core.designsystem.theme.Sky
import com.jun.tripguide_v2.core.designsystem.theme.White
import java.time.Duration

@Composable
fun RouteMarkerItem(
    isFirst: Boolean,
    isEnd: Boolean,
    isBeforeItemSelected: Boolean,
    isSelected: Boolean,
    duration: Duration,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceDim,
            modifier = Modifier
                .height(if (isSelected) 135.dp else 75.dp)
                .width(55.dp)
        ) {
            Canvas(
                modifier = modifier
                    .height(if (isSelected) 135.dp else 75.dp)
                    .width(40.dp)
            ) {
                val centerX = size.width / 2f
                val centerY = size.height / 2f
                val radius = size.width / 5f

                // 하늘색 원 그리기
                drawCircle(
                    color = if (isSelected || isBeforeItemSelected) Sky else DuskGray,
                    radius = radius,
                    center = Offset(centerX, centerY)
                )

                // 흰색 원 그리기 (하늘색 원의 중심에 위치)
                drawCircle(
                    color = White,
                    radius = radius / 2f,
                    center = Offset(centerX, centerY)
                )

                // 위 아래로 점선 그리기
                if (!isFirst) {
                    drawLine(
                        color = if (isBeforeItemSelected) Sky else DuskGray,
                        start = Offset(centerX, centerY - radius),
                        end = Offset(centerX, 0f),
                        pathEffect = if (isBeforeItemSelected) null else PathEffect.dashPathEffect(
                            floatArrayOf(4f, 8f)
                        ),
                        strokeWidth = 2.dp.toPx()
                    )
                }

                if (!isEnd) {
                    drawLine(
                        color = if (isSelected) Sky else DuskGray,
                        start = Offset(centerX, centerY + radius),
                        end = Offset(centerX, size.height),
                        pathEffect = if (isSelected) null else PathEffect.dashPathEffect(
                            floatArrayOf(4f, 8f)
                        ),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
            val hour = duration.toHoursPart()
            val minute = duration.toMinutesPart()
            AnimatedVisibility(isSelected) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .wrapContentSize(Alignment.BottomCenter)
                        .padding(bottom = 5.dp)
                ) {
                    if (hour > 0) {
                        Text(
                            text = "${hour}시간",
                            fontSize = 14.sp,
                            color = DuskGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceDim)
                        )
                    }
                    if (minute > 0) {
                        Text(
                            text = "${minute}분",
                            fontSize = 14.sp,
                            color = DuskGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceDim)
                        )
                    }
                }
            }
        }
    }
}