package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jun.tripguide_v2.core.designsystem.theme.MyTheme
import com.jun.tripguide_v2.core.designsystem.theme.Sky

@Composable
fun CustomTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationType: TopAppBarNavigationType = TopAppBarNavigationType.Back,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    containerColor: Color = Sky,
    actionButtons: @Composable () -> Unit = {},
    onNavigationClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        val icon: @Composable (Modifier, imageVector: ImageVector) -> Unit =
            { modifier, imageVector ->
                IconButton(
                    onClick = onNavigationClick,
                    modifier = modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                    )
                }
            }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor)
                .pointerInput(Unit) { /* no-op */ }
                .then(modifier)
        ) {
            if (navigationType == TopAppBarNavigationType.Back) {
                icon(
                    Modifier.align(Alignment.CenterStart),
                    Icons.Filled.ArrowBack
                )
            }
            Row(Modifier.align(Alignment.CenterEnd)) {
                actionButtons()
                if (navigationType == TopAppBarNavigationType.Close) {
                    icon(
                        Modifier,
                        Icons.Filled.Close
                    )
                }
            }
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(15.dp)
            )
        }
    }
}

enum class TopAppBarNavigationType { Back, Close, Nothing }

@Preview
@Composable
private fun TopAppBarPreviewBack() {
    MyTheme {
        CustomTopAppBar(
            title = "untitled",
            navigationType = TopAppBarNavigationType.Back
        )
    }
}

@Preview
@Composable
private fun TopAppBarPreviewClose() {
    MyTheme {
        CustomTopAppBar(
            title = "untitled",
            navigationType = TopAppBarNavigationType.Close
        )
    }
}
