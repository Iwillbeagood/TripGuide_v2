/**
 * https://github.com/commandiron/WheelPickerCompose
 * */

package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.jun.tripguide_v2.core.designsystem.theme.SkyGray
import java.time.LocalTime

@Composable
fun CustomTimePicker(
    onTimePick: (LocalTime) -> Unit,
    startTime: LocalTime = LocalTime.now(),
    size: DpSize = DpSize(128.dp, 128.dp),
    timeFormat: TimeFormat = TimeFormat.HOUR_24
) {
    WheelTimePicker(
        timeFormat = timeFormat,
        startTime = startTime,
        size = size,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = true,
            color = SkyGray,
            border = BorderStroke(2.dp, SkyGray)
        )
    ) { snappedTime ->
        onTimePick(snappedTime)
    }
}