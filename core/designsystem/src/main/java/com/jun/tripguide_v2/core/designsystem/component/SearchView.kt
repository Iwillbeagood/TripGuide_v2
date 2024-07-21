package com.jun.tripguide_v2.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jun.tripguide_v2.core.designsystem.theme.LightGray

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomSearchView(
    modifier: Modifier = Modifier,
    value: String,
    onSearch: () -> Unit = {},
    onValueChange: (String) -> Unit,
    onValueClear: () -> Unit,
    keyboardController: SoftwareKeyboardController? = null
) {
    BasicTextField(
        modifier = modifier.padding(10.dp),
        value = value,
        textStyle = TextStyle.Default.copy(fontSize = 16.sp),
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
            keyboardController?.hide()
        }),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .shadow(1.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                        )
                    }
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    innerTextField()
                }
                if (value != "") {
                    IconButton(
                        onClick = onValueClear,
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
private fun SearchviewPreview() {
    CustomSearchView(
        Modifier,
        value = "",
        {}, {}, {}, null
    )
}