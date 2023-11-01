package com.jun.tripguide_v2.feature.travelInit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.InsertDefaultTravelUsecase
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.Duration
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.Travel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TravelInitViewModel @Inject constructor(
    private val insertDefaultTravelUsecase: InsertDefaultTravelUsecase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState =
        MutableStateFlow<TravelInitUiState>(TravelInitUiState.Success())
    val uiState: StateFlow<TravelInitUiState> = _uiState

    private val _uiEffect = MutableStateFlow<TravelInitUiEffect>(TravelInitUiEffect.Idle)
    val uiEffect: StateFlow<TravelInitUiEffect> = _uiEffect

    private var contentJob: Job? = null

    fun durationPicked(duration: Duration) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                duration = duration
            )
            dialogState(false)
        }
    }

    fun meansItemPicked(type: MeansType) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                meansItems = uiState.meansItems.map {
                    it.copy(
                        isSelected = it.type == type
                    )
                }
            )
        }
    }

    fun dialogState(visibility: Boolean) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelInitUiEffect.ShowDialogForTravelDuration(visibility)
        }
    }

    fun startTimePicked(startTime: LocalTime) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                startTime = startTime
            )
        }
    }

    fun endTimePicked(endTime: LocalTime) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                endTime = endTime
            )
        }
    }

    fun addTravelComplete(
        destination: DestinationCode,
        startingPoint: String
    ) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        contentJob = viewModelScope.launch {
            flow {
                emit(
                    insertDefaultTravelUsecase(
                        Travel(
                            travelId = UUID.randomUUID().toString(),
                            destination = destination,
                            startingPoint = startingPoint,
                            startDate = uiState.duration!!.startDate,
                            endDate = uiState.duration.endDate,
                            startTime = uiState.startTime!!,
                            endTime = uiState.endTime!!,
                            meansType = uiState.meansItems.find { it.isSelected }?.type
                                ?: MeansType.CAR
                        )
                    )
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { travelId ->
                _uiEffect.value = TravelInitUiEffect.TravelInitComplete(travelId = travelId)
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