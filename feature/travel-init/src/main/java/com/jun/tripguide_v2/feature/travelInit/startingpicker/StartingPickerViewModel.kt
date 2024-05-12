package com.jun.tripguide_v2.feature.travelInit.startingpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.kakao.GetKakaoLocalByKeywordUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingPickerViewModel @Inject constructor(
    private val getKakaoLocalByKeywordUsecase: GetKakaoLocalByKeywordUsecase
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private val _uiState = MutableStateFlow<StartingPickerUiState>(StartingPickerUiState.Empty)
    val uiState: StateFlow<StartingPickerUiState> get() = _uiState

    private var contentJob: Job? = null

    fun searchAddress() {
        if (keyword.value == "") return

        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.value = StartingPickerUiState.Addresses(
                addresses = getKakaoLocalByKeywordUsecase(keyword.value)
            )
        }
    }

    fun keywordChange(keyword: String) {
        _keyword.value = keyword
    }

    fun clearKeyword() {
        _keyword.value = ""
    }
}