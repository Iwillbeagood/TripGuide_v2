package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.Sky

@Composable
fun ScrollUpButton(
    visible: Boolean,
    icon: ImageVector,
    onClicked: () -> Unit
) {
    if (!visible) return

    Button(
        shape = RoundedCornerShape(percent = 50),
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(Sky.copy(alpha = 0.8f)),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .wrapContentSize(Alignment.BottomEnd)
    )  {
        Icon(icon, null)
    }
}