package com.jun.tripguide_v2.feature.travel_meansinfo.train

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.openapi.GetTrainInfoUsecase
import com.jun.tripguide_v2.core.domain.usecase.openapi.GetTrainStationUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.UpdateTravelUsecase
import com.jun.tripguide_v2.core.model.TrainCityCode
import com.jun.tripguide_v2.core.model.TrainInfo
import com.jun.tripguide_v2.core.model.TrainStation
import com.jun.tripguide_v2.feature.travel_meansinfo.utils.epochTimeToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainInfoViewModel @Inject constructor(
    private val getTrainStationUsecase: GetTrainStationUsecase,
    private val getTrainInfoUsecase: GetTrainInfoUsecase,
    private val getTravelByIdUsecase: GetTravelByIdUsecase,
    private val updateTravelUsecase: UpdateTravelUsecase
) : ViewModel() {

    private val _trainUiState = MutableStateFlow<TrainUiState>(TrainUiState.Loading)
    val trainUiState: StateFlow<TrainUiState> get() = _trainUiState

    private val _trainDialogUiState = MutableStateFlow<TrainDialogUiState>(TrainDialogUiState.Idle)
    val trainDialogUiState: StateFlow<TrainDialogUiState> get() = _trainDialogUiState

    private val _eventFlow = MutableSharedFlow<TrainUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var contentJob: Job? = null

    fun fetchTrainInfo(travelId: String) {
        viewModelScope.launch {
            getTravelByIdUsecase(travelId).let { travel ->
                val departCityName = travel.startPlace.address
                val arriveCityName = travel.destination.destinationString.split(" ").first()

                _trainUiState.update {
                    TrainUiState.Success(
                        travel = travel,
                        departTrainStations = getTrainStationUsecase(TrainCityCode.findCityCode(name = departCityName).code),
                        arriveTrainStations = getTrainStationUsecase(TrainCityCode.findCityCode(name = arriveCityName).code)
                    )
                }
            }
        }
    }

    fun showTrainStationPickerDialog(
        stations: List<TrainStation>,
        onClick: (TrainStation) -> Unit
    ) {
        _trainDialogUiState.update {
            TrainDialogUiState.ShowTrainStationPickerDialogDialog(stations, onClick)
        }
    }

    fun showTrainInfoPickerDialog(
        isReturn: Boolean = false,
        onClick: (List<TrainInfo>) -> Unit
    ) {
        contentJob?.cancel()

        val uiState = trainUiState.value

        if (uiState !is TrainUiState.Success) return

        val firstStation =
            if (isReturn) uiState.selectedArriveStation else uiState.selectedDepartStation
        val secondStation =
            if (isReturn) uiState.selectedDepartStation else uiState.selectedArriveStation
        if (firstStation == null || secondStation == null) return

        contentJob = viewModelScope.launch {
            val trains = getTrainInfoUsecase(
                depPlaceId = firstStation.stationId,
                arrPlaceId = secondStation.stationId,
                depPlanedTime = epochTimeToString(uiState.travel.startDate)
            )

            if (trains.isEmpty()) {
                _eventFlow.emit((TrainUiEvent.ShowToast("${firstStation.stationName}에서 ${secondStation.stationName}으로 가는 기차가 없습니다.")))
            } else {
                _trainDialogUiState.update {
                    TrainDialogUiState.ShowTrainInfosPickerDialogDialog(trains, onClick)
                }
            }
        }
    }

    fun resetUiEffect() {
        _trainDialogUiState.update {
            TrainDialogUiState.Idle
        }
    }

    fun stationSelected(selectedStation: TrainStation, type: TrainInfoType) {
        val uiState = trainUiState.value

        if (uiState !is TrainUiState.Success) return

        viewModelScope.launch {
            _trainUiState.value = when (type) {
                TrainInfoType.DepartStation -> uiState.copy(
                    departTrainStations = uiState.departTrainStations.map {
                        it.copy(
                            isSelected = it == selectedStation
                        )
                    }
                )

                TrainInfoType.ArriveStation -> uiState.copy(
                    arriveTrainStations = uiState.arriveTrainStations.map {
                        it.copy(
                            isSelected = it == selectedStation
                        )
                    }
                )

                else -> uiState
            }
        }
    }

    fun trainSelected(trains: List<TrainInfo>, type: TrainInfoType) {
        val uiState = trainUiState.value
        if (uiState !is TrainUiState.Success) return

        viewModelScope.launch {
            _trainUiState.value = when (type) {
                TrainInfoType.DepartTrain -> uiState.copy(
                    trainInfos = trains
                )

                TrainInfoType.ReturnTrain -> uiState.copy(
                    returnTrainInfos = trains
                )

                else -> uiState
            }
        }
    }

    fun trainInfoComplete() {
        val uiState = trainUiState.value
        if (uiState !is TrainUiState.Success) return

        viewModelScope.launch {
            if (uiState.selectedTrainInfo == null) {
                _eventFlow.emit(TrainUiEvent.ShowToast("여행지로 가는 기차를 선택해 주세요."))
                return@launch
            }
            if (uiState.selectedReturnTrainInfo == null) {
                _eventFlow.emit(TrainUiEvent.ShowToast("돌아오는 기차를 선택해 주세요."))
                return@launch
            }

            updateTravelUsecase(
                uiState.travel.copy(
                    trainInfo = uiState.selectedTrainInfo!!,
                    returnTrainInfo = uiState.selectedReturnTrainInfo!!
                )
            )
            _eventFlow.emit(TrainUiEvent.Complete(uiState.travel.travelId.toString()))
        }
    }
}

@Stable
sealed interface TrainDialogUiState {

    @Immutable
    object Idle : TrainDialogUiState

    @Immutable
    data class ShowTrainStationPickerDialogDialog(
        val stations: List<TrainStation>,
        val onClick: (TrainStation) -> Unit
    ) : TrainDialogUiState

    @Immutable
    data class ShowTrainInfosPickerDialogDialog(
        val trains: List<TrainInfo>,
        val onClick: (List<TrainInfo>) -> Unit
    ) : TrainDialogUiState

}

@Stable
sealed interface TrainUiEvent {

    @Immutable
    data class ShowToast(val message: String) : TrainUiEvent

    @Immutable
    data class Complete(val travelId: String) : TrainUiEvent
}

enum class TrainInfoType {
    DepartStation, ArriveStation,
    DepartTrain, ReturnTrain
}