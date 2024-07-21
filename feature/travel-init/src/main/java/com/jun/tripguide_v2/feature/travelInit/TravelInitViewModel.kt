package com.jun.tripguide_v2.feature.travelInit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Address
import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansItem
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.StartingPoint
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2.feature.travelInit.util.toStringType
import com.jun.tripguide_v2.feature.travel_init.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelInitViewModel @Inject constructor(
    private val travelRepository: TravelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TravelInitUiState())
    val uiState: StateFlow<TravelInitUiState> get() = _uiState

    private val _initEffect = MutableSharedFlow<TravelInitEffect>()
    val initEffect: SharedFlow<TravelInitEffect> get() = _initEffect.asSharedFlow()

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
                    travelRepository.insertTravel(
                        Travel(
                            startPlace = Tourist(
                                title = uiState.startingName,
                                mapX = uiState.startingPoint!!.x,
                                mapY = uiState.startingPoint.y,
                                address = uiState.startingPoint.address
                            ),
                            startDate = uiState.dateDuration!!.startDate,
                            endDate = uiState.dateDuration.endDate,
                            destination = uiState.destination!!,
                        )
                    )
                )
            }.catch { throwable ->
                _initEffect.emit(TravelInitEffect.ShowErrorSnackBar(throwable))
            }.collect { travelId ->
                _initEffect.emit(TravelInitEffect.TravelInitComplete(
                    travelId = travelId,
                    uiState.selectedMeans
                ))
            }
        }
    }
}

@Immutable
data class TravelInitUiState(
    val dateDuration: DateDuration? = null,
    val startingPoint: StartingPoint? = null,
    val destination: DestinationCode? = null,
    val meansItems: List<MeansItem> = listOf(
        MeansItem(R.drawable.ic_car, R.drawable.gif_car, MeansType.CAR, isSelected = true),
        MeansItem(R.drawable.ic_train, R.drawable.gif_train, MeansType.TRAIN)
    ),
) {

    val selectedMeans: MeansType
        get() = meansItems.find { it.isSelected }?.type ?: MeansType.CAR

    val startingName get() = startingPoint?.name ?: "출발 장소를 입력해 주세요."

    val destinationName get() = destination?.destinationString ?: "여행지를 선택해 주세요."

    val dateDurationString
        get() = dateDuration?.toStringType() ?: "여행 일정을 선택해 주세요."
}

@Stable
sealed interface TravelInitEffect {

    @Immutable
    data class ShowErrorSnackBar(val error: Throwable) : TravelInitEffect

    @Immutable
    data class TravelInitComplete(val travelId: Long, val selectedMeans: MeansType) : TravelInitEffect
}