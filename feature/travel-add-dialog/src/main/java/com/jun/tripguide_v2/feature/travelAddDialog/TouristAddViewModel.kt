package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetKeywordListUsecase
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristAddViewModel @Inject constructor(
    private val getTouristsUsecase: GetTouristsUsecase,
    private val getKeywordListUsecase: GetKeywordListUsecase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TravelAddDialogUiState>(TravelAddDialogUiState.Loading)
    val uiState: StateFlow<TravelAddDialogUiState> get() = _uiState

    private val _eventFlow = MutableSharedFlow<TravelAddDialogUiEvent>()
    val eventFlow: SharedFlow<TravelAddDialogUiEvent> get() = _eventFlow.asSharedFlow()

    var tab = MutableStateFlow(TravelAddTabs.Recommend)
        private set

    var keyword = MutableStateFlow("")
        private set

    private var contentJob: Job? = null

    fun changeTab(newTabs: TravelAddTabs) {
        tab.update { newTabs }
    }


    fun initTourist(travelId: String) {
        viewModelScope.launch {
            val tourists = when (tab.value) {
                TravelAddTabs.Recommend -> getTouristsUsecase(travelId)
                TravelAddTabs.Search -> getKeywordListUsecase(keyword.value)
            }
            _uiState.update {
                TravelAddDialogUiState.S uccess(
                    travelId = travelId,
                    tourists = tourists
                )
            }
        }
    }

    fun getNextPageTourist() {
        contentJob?.cancel()

        val uiState = uiState.value
        if (uiState !is TravelAddDialogUiState.Success) return

        viewModelScope.launch {
            val newPage = uiState.pageNo + 1
            val tourists = when (tab.value) {
                TravelAddTabs.Recommend -> getTouristsUsecase(uiState.travelId, pageNo = newPage)
                TravelAddTabs.Search -> getKeywordListUsecase(keyword.value)
            }
            _uiState.update {
                uiState.copy(
                    tourists = uiState.tourists + tourists
                )
            }
        }
    }

    fun getFilterTourist(
        arrange: String,
        contentType: String
    ) {
        contentJob?.cancel()

        val uiState = uiState.value
        if (uiState !is TravelAddDialogUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.update {
                uiState.copy(
                    tourists = uiState.tourists + tourists
                )
            }

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


    fun travelRecommendComplete() {
        viewModelScope.launch {
            val tourists = uiState.value.selectedTourists.toList()
            _eventFlow.emit(TravelAddDialogUiEvent.TravelRecommendComplete(tourists))
        }
    }
}

@Stable
sealed interface TravelAddDialogUiState {

    @Immutable
    object Loading : TravelAddDialogUiState

    @Immutable
    object Empty : TravelAddDialogUiState

    @Immutable
    data class Success(
        val travelId: String = "",
        val pageNo: Int = 1,
        val tourists: List<Tourist>,
        val selectedTourists: Set<Tourist> = emptySet(),
        val arrangeTypes: List<FilterValue> = Arrange.getValues(),
        val contentTypes: List<FilterValue> = ContentType.getValues()
    ) : TravelAddDialogUiState {

        val selectedArrange: FilterValue
            get() = arrangeTypes.find { it.isSelected }!!

        val selectedContent: FilterValue
            get() = contentTypes.find { it.isSelected }!!
    }
}

@Stable
sealed interface TravelAddDialogUiEvent {

    @Immutable
    data class ShowErrorSnackBar(val throwable: Throwable) : TravelAddDialogUiEvent

    @Immutable
    data class TravelRecommendComplete(val tourists: List<Tourist>) : TravelAddDialogUiEvent
}