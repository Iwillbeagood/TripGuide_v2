package com.jun.tripguide_v2.feature.travelRecommend

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.room.InsertTouristToRouteUsecase
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetTouristsUsecase
import com.jun.tripguide_v2.core.model.Arrange
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelRecommendViewModel @Inject constructor(
    private val getTouristsUsecase: GetTouristsUsecase,
    private val insertTouristToRouteUsecase: InsertTouristToRouteUsecase
) : ViewModel() {

    private val _recommendUiState = MutableStateFlow<TravelRecommendUiState>(TravelRecommendUiState.Loading)
    val recommendUiState: StateFlow<TravelRecommendUiState> get() = _recommendUiState

    private val _eventFlow = MutableSharedFlow<TravelRecommendUiEvent>()
    val eventFlow: SharedFlow<TravelRecommendUiEvent> get() = _eventFlow.asSharedFlow()

    private var contentJob: Job? = null

    fun fetchTourist(travelId: String) {
        viewModelScope.launch {
            combine(
                recommendUiState,
                getTouristsUsecase(travelId)
            ) { uiState, tourists ->
                when (uiState) {
                    TravelRecommendUiState.Loading -> {
                        TravelRecommendUiState.Success(
                            travelId = travelId,
                            tourists = tourists
                        )
                    }

                    is TravelRecommendUiState.Success -> uiState
                }
            }.collect { _recommendUiState.value = it }
        }
    }

    fun fetchNextPageTourist() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return

        _recommendUiState.value = uiState.copy(
            pageNo = uiState.pageNo + 1
        )

        fetchNewTourist(isNextPage = true)
    }

    fun fetchNewTourist(
        isNextPage: Boolean = false
    ) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return

        contentJob = viewModelScope.launch {

            val selectedArrange = uiState.selectedArrange.value
            val selectedContentType = uiState.selectedContent.value

            combine(
                recommendUiState,
                getTouristsUsecase(
                    travelId = uiState.travelId,
                    pageNo = if (isNextPage) uiState.pageNo.toString() else "1",
                    arrange = selectedArrange,
                    contentType = selectedContentType
                )
            ) { uiState, tourists ->
                when (uiState) {
                    TravelRecommendUiState.Loading -> uiState
                    is TravelRecommendUiState.Success -> {
                        if (isNextPage) {
                            uiState.copy(
                                tourists = uiState.tourists + tourists,
                                dialogVisibility = false
                            )
                        } else {
                            uiState.copy(
                                pageNo = 1,
                                tourists = tourists,
                                dialogVisibility = false
                            )
                        }
                    }
                }
            }.collect {
                _recommendUiState.value = it
            }
        }
    }


    fun changeTouristSelection(tourist: Tourist) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return

        contentJob = viewModelScope.launch {
            val newTourists = uiState.tourists.map {
                if (it == tourist) {
                    it.copy(isSelected = !it.isSelected)
                } else {
                    it
                }
            }
            _recommendUiState.value = uiState.copy(
                tourists = newTourists,
                selectedTourists = uiState.selectedTourists + newTourists.filter { it.isSelected }
            )
        }
    }

    fun changeDialogVisibility() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return

        contentJob = viewModelScope.launch {
            _recommendUiState.value = uiState.copy(
                dialogVisibility = !uiState.dialogVisibility
            )
        }
    }

    fun changeSortBy(value: FilterValue) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return

        contentJob = viewModelScope.launch {
            _recommendUiState.value = uiState.copy(
                arrangeTypes = uiState.arrangeTypes.map {
                    it.copy(
                        isSelected = it == value
                    )
                }
            )
        }
    }

    fun changeTouristTypeBy(value: FilterValue) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return

        contentJob = viewModelScope.launch {
            _recommendUiState.value = uiState.copy(
                contentTypes = uiState.contentTypes.map {
                    it.copy(
                        isSelected = it == value
                    )
                }
            )
        }
    }

    fun scrollToFirstItem() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _eventFlow.emit(TravelRecommendUiEvent.ScrollToFirstItem)
        }
    }

    fun travelRecommendComplete(orderNum: Int) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = recommendUiState.value

        if (uiState !is TravelRecommendUiState.Success) return



        contentJob = viewModelScope.launch {
            if (uiState.selectedTourists.isEmpty()) {
                _eventFlow.emit(TravelRecommendUiEvent.TravelRecommendComplete)
            } else {
                insertTouristToRouteUsecase(
                    uiState.travelId,
                    uiState.selectedTourists.toList(),
                    orderNum
                )
                _eventFlow.emit(TravelRecommendUiEvent.TravelRecommendComplete)
            }
        }
    }
}

@Stable
sealed interface TravelRecommendUiState {

    @Immutable
    object Loading : TravelRecommendUiState

    @Immutable
    data class Success(
        val travelId: String = "",
        val pageNo: Int = 1,
        val tourists: List<Tourist>,
        val selectedTourists: Set<Tourist> = emptySet(),
        val arrangeTypes: List<FilterValue> = Arrange.getValues(),
        val contentTypes: List<FilterValue> = ContentType.getValues(),
        val dialogVisibility: Boolean = false
    ) : TravelRecommendUiState {

        val selectedArrange: FilterValue
            get() = arrangeTypes.find { it.isSelected }!!

        val selectedContent: FilterValue
            get() = contentTypes.find { it.isSelected }!!
    }
}

@Stable
sealed interface TravelRecommendUiEvent {

    @Immutable
    data class ShowErrorSnackBar(val throwable: Throwable) : TravelRecommendUiEvent

    @Immutable
    object ScrollToFirstItem : TravelRecommendUiEvent

    @Immutable
    object TravelRecommendComplete : TravelRecommendUiEvent
}