package com.jun.tripguide_v2.feature.travelInit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.openapi.GetAirplaneSchedulesUsecase
import com.jun.tripguide_v2.core.domain.usecase.openapi.GetTrainInfoUsecase
import com.jun.tripguide_v2.core.domain.usecase.openapi.GetTrainStationUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.InsertDefaultTravelUsecase
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.StartingPoint
import com.jun.tripguide_v2.core.model.TrainCityCode
import com.jun.tripguide_v2.core.model.TrainStation
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
    private val insertDefaultTravelUsecase: InsertDefaultTravelUsecase,
    private val airplaneSchedulesUsecase: GetAirplaneSchedulesUsecase,
    private val trainStationUsecase: GetTrainStationUsecase,
    private val trainInfoUsecase: GetTrainInfoUsecase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState =
        MutableStateFlow<TravelInitUiState>(TravelInitUiState.Success())
    val uiState: StateFlow<TravelInitUiState> get() = _uiState

    private val _uiEffect = MutableStateFlow<TravelInitUiEffect>(TravelInitUiEffect.Idle)
    val uiEffect: StateFlow<TravelInitUiEffect> get() = _uiEffect

    private var contentJob: Job? = null

    fun fetchDepartTrainStations(startingPoint: StartingPoint) {
        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        viewModelScope.launch {
            val name = startingPoint.address

            _uiState.value = uiState.copy(
                departTrainStations = trainStationUsecase(TrainCityCode.findCityCode(name = name).code)
            )
        }
    }

    fun fetchArriveTrainStations(destination: DestinationCode) {
        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        viewModelScope.launch {
            val name = destination.destinationString.split(" ").first()

            _uiState.value = uiState.copy(
                arriveTrainStations = trainStationUsecase(TrainCityCode.findCityCode(name = name).code)
            )
        }
    }

//    fun fetchDepartTrainInfo() {
//        val uiState = uiState.value
//
//        if (uiState !is TravelInitUiState.Success) return
//
//        viewModelScope.launch {
//            val name = destination.destinationString.split(" ").first()
//
//            _uiState.value = uiState.copy(
//                arriveTrainStations = trainStationUsecase(TrainCityCode.findCityCode(name = name).code)
//            )
//        }
//    }
//
//    fun fetchReturnTrainInfo() {
//        val uiState = uiState.value
//
//        if (uiState !is TravelInitUiState.Success) return
//
//        viewModelScope.launch {
//            val name = destination.destinationString.split(" ").first()
//
//            _uiState.value = uiState.copy(
//                arriveTrainStations = trainStationUsecase(TrainCityCode.findCityCode(name = name).code)
//            )
//        }
//    }

    fun durationPicked(dateDuration: DateDuration) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                dateDuration = dateDuration
            )
            _uiEffect.value = TravelInitUiEffect.Idle
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

    fun showTravelDurationDialog() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = TravelInitUiEffect.ShowTravelDurationDialog
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

    fun addTravelComplete(
        destination: DestinationCode?,
        startingPoint: StartingPoint?
    ) {
        if (destination == null || startingPoint == null) return

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
                            startDate = uiState.dateDuration!!.startDate,
                            endDate = uiState.dateDuration.endDate,
                            startTime = uiState.startTime!!,
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

    fun changeDropDownExpanded(type: StationType) {
        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        _uiState.value = when(type) {
            StationType.DEPART ->  uiState.copy(
                departDropDownExpanded = !uiState.departDropDownExpanded
            )
            StationType.ARRIVE ->  uiState.copy(
                arriveDropDownExpanded = !uiState.arriveDropDownExpanded
            )
        }
    }

    fun arriveStationSelected(selectedStation: TrainStation, type: StationType) {
        changeDropDownExpanded(type)

        val uiState = uiState.value

        if (uiState !is TravelInitUiState.Success) return

        _uiState.value = when(type) {
            StationType.DEPART -> uiState.copy(
                departTrainStations = uiState.departTrainStations.map {
                    it.copy(
                        isSelected = it == selectedStation
                    )
                }
            )
            StationType.ARRIVE -> uiState.copy(
                arriveTrainStations = uiState.arriveTrainStations.map {
                    it.copy(
                        isSelected = it == selectedStation
                    )
                }
            )
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