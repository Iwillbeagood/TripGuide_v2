package com.jun.tripguide_v2.feature.addtravel.startingpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.GetKakaoLocalByKeywordUsecase
import com.jun.tripguide_v2.core.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartingPickerViewModel @Inject constructor(
    private val getKakaoLocalByKeywordUsecase: GetKakaoLocalByKeywordUsecase
) : ViewModel() {

    /**
     * 상태와 Effect 모두 있어야 함
     * init은 필요가 없음
     *
     * uiState에는 empty와 Search
     *
     * search하는 메서드가 있어 키워드로 검색 후 이를 상태에 반영
     *
     * 검색 리스트를 사용자에게 보여줌 간단!
     *
     * */

    private val _uiState =
        MutableStateFlow<StartingPickerUiState>(StartingPickerUiState.Empty)
    val uiState: StateFlow<StartingPickerUiState> = _uiState

    private val _uiEffect = MutableStateFlow<StartingPickerUiEffect>(StartingPickerUiEffect.Idle)
    val uiEffect: StateFlow<StartingPickerUiEffect> = _uiEffect

    private var contentJob: Job? = null

    fun clearKeyword() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.value = StartingPickerUiState.Keyword(
                keyword = ""
            )
        }
    }

    fun searchAddress(keyword: String) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.value = StartingPickerUiState.Keyword(
                keyword = keyword
            )

            _uiState.value = StartingPickerUiState.Addresses(
                addresses = getKakaoLocalByKeywordUsecase(keyword)
            )
        }
    }

    fun addressPicked(address: Address) {

    }
}