package com.jun.tripguide_v2.feature.travelInit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.room.InsertDefaultTravelUsecase
import com.jun.tripguide_v2.core.model.Address
import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.StartingPoint
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.core.model.Travel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TravelInitViewModel @Inject constructor(
    private val insertDefaultTravelUsecase: InsertDefaultTravelUsecase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow(TravelInitUiState())
    val uiState: StateFlow<TravelInitUiState> get() = _uiState

    private val _uiEffect = MutableStateFlow<TravelInitUiEffect>(TravelInitUiEffect.Idle)
    val uiEffect: StateFlow<TravelInitUiEffect> get() = _uiEffect

    private var contentJob: Job? = null

    fun durationPicked(dateDuration: DateDuration) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    dateDuration = dateDuration
                )
            }
            _uiEffect.value = TravelInitUiEffect.Idle
        }
    }

    fun meansItemPicked(type: MeansType) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.update { initUiState ->
                initUiState.copy(
                    meansItems = initUiState.meansItems.map {
                        it.copy(
                            isSelected = it.type == type
                        )
                    }
                )
            }
        }
    }

    fun showTravelDurationDialog() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelInitUiEffect.ShowTravelDurationDialog
        }
    }

    fun showDestinationPickerDialog() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelInitUiEffect.ShowDestinationPickerDialog
        }
    }

    fun showStartingPickerDialog() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelInitUiEffect.ShowStartingPickerDialog
        }
    }

    fun startingPicked(startingPoint: Address) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    startingPoint = StartingPoint(
                        name = startingPoint.name,
                        x = startingPoint.x.toDouble(),
                        y = startingPoint.y.toDouble(),
                        address = startingPoint.address
                    )
                )
            }
        }
    }

    fun destinationPicked(destination: DestinationCode?) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    destination = destination
                )
            }
        }
    }

    fun addTravelComplete() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value
        contentJob = viewModelScope.launch {
            flow {
                emit(
                    insertDefaultTravelUsecase(
                        Travel(
                            startPlace = Tourist(
                                title = uiState.startingName,
                                mapX = uiState.startingPoint!!.x,
                                mapY = uiState.startingPoint.y,
                                address = uiState.startingPoint.address
                            ),
                            destination = uiState.destination!!,
                        )
                    )
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { travelId ->
                _uiEffect.value = TravelInitUiEffect.TravelInitComplete(
                    travelId = travelId,
                    uiState.selectedMeans
                )
            }
        }
    }

    fun resetUiEffect() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelInitUiEffect.Idle
        }
    }
}