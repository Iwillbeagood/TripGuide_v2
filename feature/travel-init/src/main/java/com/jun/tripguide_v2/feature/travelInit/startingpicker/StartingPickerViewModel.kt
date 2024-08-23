package com.jun.tripguide_v2.feature.travelInit.startingpicker

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.GetKakaoLocalByKeywordUsecase
import com.jun.tripguide_v2.core.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StartingPickerViewModel @Inject constructor(
    private val getKakaoLocalByKeywordUsecase: GetKakaoLocalByKeywordUsecase
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    val uiState: StateFlow<StartingPickerUiState> = keyword
        .debounce(300)
        .filter { it.isNotBlank() }
        .flatMapLatest { keyword ->
            flow {
                val results = getKakaoLocalByKeywordUsecase(keyword).toPersistentList()
                if (results.isEmpty()) {
                    emit(StartingPickerUiState.Empty)
                } else {
                    emit(StartingPickerUiState.Addresses(results))
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StartingPickerUiState.Empty
        )

    fun keywordChange(keyword: String) {
        _keyword.value = keyword
    }

    fun clearKeyword() {
        _keyword.value = ""
    }
}

@Stable
sealed interface StartingPickerUiState {

    @Immutable
    object Empty : StartingPickerUiState

    @Immutable
    data class Addresses(val addresses: List<Address> = emptyList()) : StartingPickerUiState
}