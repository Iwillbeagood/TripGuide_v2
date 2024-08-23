package com.jun.tripguide_v2.feature.mytravelPlan

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTravelPlanViewModel @Inject constructor(
    private val travelRepository: TravelRepository
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<MyTravelPlanUiState>(MyTravelPlanUiState.Loading)
    val uiState: StateFlow<MyTravelPlanUiState> get() = _uiState

    fun getTravelRoute(travelId: String) {
        viewModelScope.launch {
            val travel = travelRepository.getTravelById(travelId)
            _uiState.update {
                MyTravelPlanUiState.Routes(travel)
            }
        }
    }
}

@Stable
sealed interface MyTravelPlanUiState {
    @Immutable
    data object Loading : MyTravelPlanUiState

    @Immutable
    data class Routes(
        val travel: Travel
    ) : MyTravelPlanUiState {
        val routes get() = listOf(travel.startPlace) + travel.places
    }
}