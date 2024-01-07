@file:OptIn(ExperimentalComposeUiApi::class)

package com.jun.tripguide_v2.feature.travelInit.startingpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomSearchView
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.Black
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.PaperGray
import com.jun.tripguide_v2.core.model.Address

@Composable
fun StartingPickerScreen(
    onBackClick: () -> Unit,
    onBackClickAddress: (Address) -> Unit,
    viewModel: StartingPickerViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.uiEffect.collectAsStateWithLifecycle()
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(effect) {
        val startingPoint = (effect as? StartingPickerUiEffect.StartingPicked)?.address

        if (startingPoint != null)
            onBackClickAddress(startingPoint)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperGray)
    ) {
        CustomTopAppBar(
            title = "출발 장소",
            navigationType = TopAppBarNavigationType.Close,
            onNavigationClick = onBackClick
        )
        CustomSearchView(
            value = keyword,
            onSearch = viewModel::searchAddress,
            onValueChange = viewModel::keywordChange,
            onValueClear = viewModel::clearKeyword,
            keyboardController = keyboardController
        )
        AddressItemsColumn(
            addresses = (uiState as? StartingPickerUiState.Addresses)?.addresses ?: emptyList(),
            onClickAddressItem = viewModel::addressPicked
        )
    }
}

@Composable
fun AddressItemsColumn(
    addresses: List<Address>,
    onClickAddressItem: (Address) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(
            items = addresses,
            key = { item -> item.id }
        ) { item ->
            AddressItem(
                address = item,
                textColor = Black,
                modifier = Modifier
                    .fillMaxSize()
                    .background(PaperGray)
                    .clickable {
                        onClickAddressItem(item)
                    }
            )
            Divider(
                color = Gray,
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
            )
        }
    }
}

@Composable
fun AddressItem(
    address: Address,
    textColor: Color,
    modifier: Modifier
) {
    Column(
        modifier = modifier.background(PaperGray)
    ) {
        Text(
            text = address.name,
            color = textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .background(PaperGray)
                .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = address.address,
            color = textColor,
            fontSize = 10.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .background(PaperGray)
                .padding(start = 16.dp, top = 5.dp, bottom = 16.dp)
        )
    }
}