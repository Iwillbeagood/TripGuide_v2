package com.jun.tripguide_v2.feature.travelSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.room.InsertTouristToRouteUsecase
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
    private val getKeywordListUsecase: GetKeywordListUsecase,
    private val insertTouristToRouteUsecase: InsertTouristToRouteUsecase,
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _travelSearchUiState =
        MutableStateFlow<TravelSearchUiState>(TravelSearchUiState.Success())
    val travelSearchUiState: StateFlow<TravelSearchUiState> get() = _travelSearchUiState

    private val _travelSearchUiEffect =
        MutableStateFlow<TravelSearchUiEffect>(TravelSearchUiEffect.Idle)
    val travelSearchUiEffect: StateFlow<TravelSearchUiEffect> get() = _travelSearchUiEffect

    private var contentJob: Job? = null

    fun setTravelId(travelId: String) {
        viewModelScope.launch {
            _travelSearchUiState.value = TravelSearchUiState.Success(
                travelId = travelId
            )
        }
    }

    fun searchTourist() {
        if (keyword.value == "") return

        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = travelSearchUiState.value

        if (uiState !is TravelSearchUiState.Success) return

        contentJob = viewModelScope.launch {
            _travelSearchUiState.value = uiState.copy(
                tourists = getKeywordListUsecase(keyword.value)
            )
        }
    }

    fun changeTouristSelection(tourist: Tourist) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = travelSearchUiState.value

        if (uiState !is TravelSearchUiState.Success) return

        contentJob = viewModelScope.launch {
            _travelSearchUiState.value = uiState.copy(
                tourists = uiState.tourists.map {
                    if (it == tourist) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun keywordChange(keyword: String) {
        _keyword.value = keyword
    }

    fun clearKeyword() {
        _keyword.value = ""
    }

    fun scrollToFirstItem() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _travelSearchUiEffect.value = TravelSearchUiEffect.ScrollToFirstItem
        }
    }

    fun resetUiEffect() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _travelSearchUiEffect.value = TravelSearchUiEffect.Idle
        }
    }

    fun travelSearchComplete(orderNum: Int) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = travelSearchUiState.value

        if (uiState !is TravelSearchUiState.Success) return

        if (uiState.selectedTourists.isEmpty()) {
            _travelSearchUiEffect.value = TravelSearchUiEffect.TravelSearchComplete
            return
        }

        contentJob = viewModelScope.launch {
            insertTouristToRouteUsecase(uiState.travelId, uiState.selectedTourists.toList(), orderNum)

            _travelSearchUiEffect.value = TravelSearchUiEffect.TravelSearchComplete
        }
    }
}