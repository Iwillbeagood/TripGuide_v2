package com.jun.tripguide_v2.feature.addtravel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.GetAreaCodeUsecase
import com.jun.tripguide_v2.core.domain.usecase.GetDefaultAreaCodeUsecase
import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.core.model.MeansType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTravelViewModel @Inject constructor(

) : ViewModel() {

    private val _addTravelUiState =
        MutableStateFlow<AddTravelUiState>(AddTravelUiState.MeansItemState())
    val addTravelUiState: StateFlow<AddTravelUiState> = _addTravelUiState

    init {
//        viewModelScope.launch {
//            combine(
//                addTravelUiState,
//                getDefaultAreaCodeUsecase()
//            ) { addTravelUiState, defaultAreaCodes ->
//                when (addTravelUiState) {
//                    AddTravelUiState.Loading -> addTravelUiState
//                    is AddTravelUiState.MeansItemState -> addTravelUiState
//                    is AddTravelUiState.PickDestination -> {
//                        addTravelUiState.copy(defaultAreaCodes = defaultAreaCodes)
//                    }
//                }
//            }.collect { _addTravelUiState.value = it }
//        }
    }

    private val _addTravelUiEffect = MutableStateFlow<AddTravelUiEffect>(AddTravelUiEffect.Idle)
    val addTravelUiEffect: StateFlow<AddTravelUiEffect> = _addTravelUiEffect

    // 여행 목적지를 선택한 경우에 UI로 보낼 effect
    fun destinationPicked(areaCode: AreaCode) {

    }

    // 여행 출발지를 선택한 경우에 UI로 보낼 effect
    fun startingPointPicked() {

    }

    // 여행 이동 수단을 선택한 경우에 UI로 보낼 effect
    fun meansItemPicked(type: MeansType) {
        val uiState = addTravelUiState.value

        if (uiState !is AddTravelUiState.MeansItemState) return

        _addTravelUiState.value = uiState.copy(
            meansItems = uiState.meansItems.map {
                it.copy(
                    isSelected = it.type == type
                )
            }
        )
    }

}