package com.jun.tripguide_v2.feature.travel_meansinfo.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.UpdateTravelUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CarInfoViewModel @Inject constructor(
    private val getTravelByIdUsecase: GetTravelByIdUsecase,
    private val updateTravelUsecase: UpdateTravelUsecase
) : ViewModel() {

    private var startTime = MutableStateFlow(LocalTime.now())

    private var contentJob: Job? = null

    fun startTimePicked(newTime: LocalTime) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            startTime.update { newTime }
        }
    }

    fun carInfoComplete(travelId: String) {
        viewModelScope.launch {
            val travel = getTravelByIdUsecase(travelId)
            val arrivalDate = travel.startPlace.arrivalDateTime.toLocalDate()
            updateTravelUsecase(
                travel.copy(
                startPlace = travel.startPlace.copy(
                    arrivalDateTime = startTime.value.atDate(arrivalDate)
                )
            ))
        }
    }
}