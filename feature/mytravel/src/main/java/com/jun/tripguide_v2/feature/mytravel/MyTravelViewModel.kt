package com.jun.tripguide_v2.feature.mytravel

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.room.DeleteTravelUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelsUsecase
import com.jun.tripguide_v2.core.model.Travel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTravelViewModel @Inject constructor(
    private val getTravelsUsecase: GetTravelsUsecase,
    private val deleteTravelUsecase: DeleteTravelUsecase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<MyTravelUiState>(MyTravelUiState.Loading)
    val uiState: StateFlow<MyTravelUiState> get() = _uiState

    private val _uiEffect = MutableStateFlow<MyTravelUiEffect>(MyTravelUiEffect.Idle)
    val uiEffect: StateFlow<MyTravelUiEffect> get() = _uiEffect

    init {
        viewModelScope.launch {
            flow { emit(getTravelsUsecase()) }
                .map { travels ->
                    if (travels.isNotEmpty()) {
                        MyTravelUiState.Travels(travels)
                    } else {
                        MyTravelUiState.Empty
                    }
                }.collect {
                    _uiState.value = it
                }
        }
    }

    fun showDeleteConfirmationDialog(travel: Travel) {
        _uiEffect.value = MyTravelUiEffect.ShowDeleteDialog(travel)
    }

    fun deleteSelectedTravel(travel: Travel) {

        val uiState = uiState.value

        if (uiState !is MyTravelUiState.Travels) return

        viewModelScope.launch {
            deleteTravelUsecase(travel)
            _uiState.value = uiState.copy(travels = uiState.travels.toMutableList().apply { remove(travel) })
            resetUiEffect()
        }
    }

    fun resetUiEffect() {
        _uiEffect.value = MyTravelUiEffect.Idle
    }

}

@Stable
sealed interface MyTravelUiState {

    @Immutable
    object Loading : MyTravelUiState

    @Immutable
    object Empty : MyTravelUiState

    @Immutable
    data class Travels(
        val travels: List<Travel> = emptyList()
    ): MyTravelUiState {

        val previousTravels : List<Travel>
            get() = travels.filter { it.endDate < currentMillis }

        val currentTravels : List<Travel>
            get() = travels.filter { currentMillis in it.startDate..it.endDate }

        val planedTravels : List<Travel>
            get() = travels.filter { it.startDate > currentMillis }


        companion object {
            private val currentMillis = System.currentTimeMillis()
        }
    }
}

sealed interface MyTravelUiEffect {

    object Idle : MyTravelUiEffect

    data class ShowDeleteDialog(val travel: Travel) : MyTravelUiEffect
}