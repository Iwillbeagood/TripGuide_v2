/*
* https://semicolonspace.com/jetpack-compose-date-picker-material3/
* */
package com.jun.tripguide_v2.feature.travelInit.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.feature.travelInit.util.getFormattedDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationDatePicker(
    onBackClick: () -> Unit,
    onDurationPick: (DateDuration) -> Unit
) {
    val currentDateTime: LocalDateTime = LocalDateTime.now()
    val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    val formattedDateTime: String = currentDateTime.format(formatter)

    val calendar = Calendar.getInstance()

    val (year, month, day) = formattedDateTime.split("-").map { it.toInt() }

    calendar.set(year, month - 1, day)

    val startDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    calendar.set(year, month - 1, day + 1)

    val endDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate,
        initialSelectedEndDateMillis = endDate
    )

    DatePickerDialog(
        onDismissRequest = onBackClick,
        confirmButton = {
            TextButton(onClick = {
                onDurationPick(
                    DateDuration(
                        startDate = dateRangePickerState.selectedStartDateMillis ?: 0,
                        endDate = dateRangePickerState.selectedEndDateMillis ?: 0
                    )
                )
            }) {
                Text(text = "확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onBackClick) {
                Text(text = "취소")
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            colors = DatePickerDefaults.colors(
                titleContentColor = Black,
                headlineContentColor = Black,
                weekdayContentColor = Black,
                subheadContentColor = Black,
                yearContentColor = Black
            ),
            modifier = Modifier.height(height = 500.dp),
            title = {
                Text(
                    text = "여행 기간을 선택해 주세요.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            },
            headline = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Box(Modifier.weight(1f)) {
                        Text(
                            text = dateRangePickerState.selectedStartDateMillis?.getFormattedDate() ?: "Start Date"
                        )
                    }
                    Box(Modifier.weight(0.2f)) {
                        Text(text = "~")
                    }
                    Box(Modifier.weight(1f)) {
                        Text(
                            text = dateRangePickerState.selectedEndDateMillis?.getFormattedDate() ?: "End Date"
                        )
                    }
                }
            }
        )
    }
}
