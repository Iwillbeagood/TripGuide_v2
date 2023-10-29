package com.jun.tripguide_v2.feature.travelroute

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetAreaBaseListUsecase
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetSigunguBaseListUsecase
import com.jun.tripguide_v2.core.domain.usecase.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetAreaBaseListWithTypeUsecase
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetSigunguBaseListByTypeUsecase
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class TravelRouteViewModel @Inject constructor(
    private val getTravelByIdUsecase: GetTravelByIdUsecase,
    private val getAreaBaseListUsecase: GetAreaBaseListUsecase,
    private val getSigunguBaseListUsecase: GetSigunguBaseListUsecase,
    private val getAreaBaseListWithTypeUsecase: GetAreaBaseListWithTypeUsecase,
    private val getSigunguBaseListByTypeUsecase: GetSigunguBaseListByTypeUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TravelRouteUiState>(TravelRouteUiState.Loading)
    val uiState: StateFlow<TravelRouteUiState> = _uiState

    private val _uiEffect = MutableStateFlow<TravelRouteUiEffect>(TravelRouteUiEffect.Idle)
    val uiEffect: StateFlow<TravelRouteUiEffect> = _uiEffect

    private var contentJob: Job? = null

    fun fetchTourist(travelId: String) {
        viewModelScope.launch {
            val travelFlow = flow { emit(getTravelByIdUsecase(travelId)) }

            val touristInfoFlow = travelFlow.flatMapConcat { travel ->
                flow {
                    emit(
                        if (travel.destination.sigunguCode == "0") {
                            getAreaBaseListUsecase("1", "P", travel.destination.areaCode)
                        } else {
                            getSigunguBaseListUsecase(
                                "1",
                                "P",
                                travel.destination.areaCode,
                                travel.destination.sigunguCode
                            )
                        }
                    )
                }
            }

            combine(
                travelFlow,
                touristInfoFlow
            ) { travel, touristInfoList ->
                TravelRouteUiState.Success(
                    travel = travel,
                    touristList = touristInfoList
                )
            }.collect { _uiState.value = it }
        }
    }

    fun fetchNextPageTourist() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelRouteUiState.Success) return

        _uiState.value = uiState.copy(
            pageNo = uiState.pageNo + 1
        )

        fetchNewTourist(isNewPage = true)
    }

    fun fetchNewTourist(
        isFilter: Boolean = false,
        isNewPage: Boolean = false
    ) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelRouteUiState.Success) return

        val travel = uiState.travel
        val pageNo = uiState.pageNo.toString()

        contentJob = viewModelScope.launch {

            val selectedSort = uiState.sortByList.find { it.isSelected }?.value ?: "P"
            val selectedType = uiState.touristTypeList.find { it.isSelected }?.value ?: ""

            val tourist = if (selectedType == "") {
                if (travel.destination.sigunguCode == "0") {
                    getAreaBaseListUsecase(pageNo, selectedSort, travel.destination.areaCode)
                } else {
                    getSigunguBaseListUsecase(
                        pageNo,
                        selectedSort,
                        travel.destination.areaCode,
                        travel.destination.sigunguCode
                    )
                }
            } else {
                if (travel.destination.sigunguCode == "0") {
                    getAreaBaseListWithTypeUsecase(pageNo, selectedSort, travel.destination.areaCode, selectedType)
                } else {
                    getSigunguBaseListByTypeUsecase(
                        pageNo,
                        selectedSort,
                        travel.destination.areaCode,
                        travel.destination.sigunguCode,
                        selectedType
                    )
                }
            }

            if (isFilter) {
                _uiState.value = uiState.copy(
                    touristList = tourist,
                    dialogVisibility = false
                )
            }

            if (isNewPage) {
                _uiState.value = uiState.copy(
                    touristList = uiState.touristList + tourist,
                    dialogVisibility = false
                )
            }
        }
    }


    fun changeTouristSelection(tourist: Tourist) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelRouteUiState.Success) return

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

    fun changeDialogVisibility() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelRouteUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                dialogVisibility = !uiState.dialogVisibility
            )
        }
    }

    fun changeSortBy(value: FilterValue) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelRouteUiState.Success) return

        Log.d("TAG", "changeSortBy: $value")
        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                sortByList = uiState.sortByList.map {
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

        val uiState = uiState.value

        if (uiState !is TravelRouteUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                touristTypeList = uiState.touristTypeList.map {
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
            _uiEffect.value = TravelRouteUiEffect.ScrollToFirstItem
        }
    }

    fun resetUiEffect() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelRouteUiEffect.Idle
        }
    }
}