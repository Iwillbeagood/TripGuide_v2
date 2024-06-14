package com.jun.tripguide_v2.feature.travelInit.startingpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jun.tripguide_v2.core.designsystem.component.CustomSearchView
import com.jun.tripguide_v2.core.designsystem.component.CustomTopAppBar
import com.jun.tripguide_v2.core.designsystem.component.TopAppBarNavigationType
import com.jun.tripguide_v2.core.designsystem.theme.Gray
import com.jun.tripguide_v2.core.designsystem.theme.surfaceDim
import com.jun.tripguide_v2.core.model.Address

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StartingPickerDialog(
    onStartingPick: (Address) -> Unit,
    onDismissRequest: () -> Unit,
    viewModel: StartingPickerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceDim)
        ) {
            CustomTopAppBar(
                title = "출발 장소",
                navigationType = TopAppBarNavigationType.Close,
                onNavigationClick = onDismissRequest
            )
            CustomSearchView(
                value = keyword,
                onValueChange = viewModel::keywordChange,
                onValueClear = viewModel::clearKeyword,
                keyboardController = keyboardController
            )
            StartingPickerContent(
                uiState = uiState,
                onClickAddressItem = {
                    onDismissRequest()
                    onStartingPick(it)
                }
            )
        }
    }
}

@Composable
private fun StartingPickerContent(
    uiState: StartingPickerUiState,
    onClickAddressItem: (Address) -> Unit
) {
    when(uiState) {
        StartingPickerUiState.Empty -> AddressEmptyScreen()
        is StartingPickerUiState.Addresses -> AddressItemsColumn(
            addresses = uiState.addresses,
            onClickAddressItem = onClickAddressItem
        )
    }
}

@Composable
private fun AddressEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "검색 결과가 존재하지 않습니다.",modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun AddressItemsColumn(
    addresses: List<Address>,
    onClickAddressItem: (Address) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = addresses,
            key = { item -> item.id }
        ) { item ->
            AddressItem(
                address = item,
                modifier = Modifier
                    .fillMaxSize()
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
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = address.name,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = address.address,
            fontSize = 10.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 16.dp, top = 5.dp, bottom = 16.dp)
        )
    }
}