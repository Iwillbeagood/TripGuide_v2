package com.jun.tripguide_v2.feature.travelSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetKeywordListUsecase
import com.jun.tripguide_v2.core.model.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelSearchViewModel @Inject constructor(
    private val getKeywordListUsecase: GetKeywordListUsecase
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<TravelSearchUiState>(TravelSearchUiState.TouristList())
    val uiState: StateFlow<TravelSearchUiState> = _uiState

    private val _uiEffect = MutableStateFlow<TravelSearchUiEffect>(TravelSearchUiEffect.Idle)
    val uiEffect: StateFlow<TravelSearchUiEffect> = _uiEffect

    private var contentJob: Job? = null

    fun searchTourist(keyword: String) {
        if (keyword == "") return

        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _keyword.value = keyword
            _uiState.value = TravelSearchUiState.TouristList(
                touristList = getKeywordListUsecase(keyword)
            )
        }
    }

    fun searchNextPageTourist() {

    }

    fun changeTouristSelection(tourist: Tourist) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelSearchUiState.TouristList) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                touristList = uiState.touristList.map {
                    if (it == tourist) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun clearKeyword() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _keyword.value = ""
        }
    }

    fun scrollToFirstItem() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelSearchUiEffect.ScrollToFirstItem
        }
    }

    fun resetUiEffect() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelSearchUiEffect.Idle
        }
    }

    fun travelSearchComplete() {

    }
}