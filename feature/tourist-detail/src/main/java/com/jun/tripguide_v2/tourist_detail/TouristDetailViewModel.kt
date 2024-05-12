package com.jun.tripguide_v2.tourist_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetDetailInfoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristDetailViewModel @Inject constructor(
    private val getDetailInfoUsecase: GetDetailInfoUsecase
): ViewModel() {

    private val _uiState = MutableStateFlow<TouristDetailUiState>(TouristDetailUiState.Loading)
    val uiState: StateFlow<TouristDetailUiState> get() = _uiState

    fun fetchTouristDetail(touristId: String) {
        viewModelScope.launch {
            combine(
                uiState,
                flow { emit(getDetailInfoUsecase(touristId)) }
            ) { uiState, (commonInfo, detailIntro) ->
                when(uiState) {
                    TouristDetailUiState.Loading -> {
                        TouristDetailUiState.Success(commonInfo, detailIntro)
                    }
                    is TouristDetailUiState.Success -> uiState
                }
            }.collect {
                _uiState.value = it
            }
        }
    }
}

