package com.jun.tripguide_v2.feature.travelInit.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import java.time.LocalTime

@Composable
fun TimePicker(
    onTimePick: (LocalTime) -> Unit
) {
    WheelTimePicker(
        timeFormat = TimeFormat.AM_PM,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = true,
            color = SkyGray,
            border = BorderStroke(2.dp, SkyGray)
        )
    ) { snappedTime ->
        onTimePick(snappedTime)
    }
}
@Composable
fun TimePickerSection(
    visibility: Boolean,
    onStartTimePicked: (LocalTime) -> Unit,
    onEndTimePicked: (LocalTime) -> Unit
) {
    if (!visibility) return

    Spacer(Modifier.height(25.dp))
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "여행 출발 시간",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "여행 종료 시간",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TimePicker(onTimePick = onStartTimePicked)
            TimePicker(onTimePick = onEndTimePicked)
        }
    }

}

@Preview
@Composable
fun TimePickerPre() {
    TimePicker {

    }
}
