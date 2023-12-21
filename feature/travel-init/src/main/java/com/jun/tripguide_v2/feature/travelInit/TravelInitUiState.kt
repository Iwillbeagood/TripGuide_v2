package com.jun.tripguide_v2.feature.travelInit

import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.MeansItem
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.TrainInfo
import com.jun.tripguide_v2.core.model.TrainStation
import com.jun.tripguide_v2.feature.travel_init.R
import java.time.LocalTime

sealed interface TravelInitUiState {

    data class Success(
        val dateDuration: DateDuration? = null,
        val meansItems: List<MeansItem> = listOf(
            MeansItem(R.drawable.ic_car, R.drawable.gif_car, MeansType.CAR, isSelected = true),
            MeansItem(R.drawable.ic_airplane, R.drawable.gif_airplane, MeansType.PLANE),
            MeansItem(R.drawable.ic_train, R.drawable.gif_train, MeansType.TRAIN)
        ),
        val startTime: LocalTime? = null,
        val departDropDownExpanded: Boolean = false,
        val arriveDropDownExpanded: Boolean = false,
        val departTrainDropDownExpanded: Boolean = false,
        val returnTrainDropDownExpanded: Boolean = false,
        val departTrainStations: List<TrainStation> = emptyList(),
        val arriveTrainStations: List<TrainStation> = emptyList(),
        val departTrainInfos: List<TrainInfo> = emptyList(),
        val returnTrainInfos: List<TrainInfo> = emptyList(),
    ): TravelInitUiState {

        val selectedMeans: MeansType
            get() = meansItems.find { it.isSelected }?.type ?: MeansType.CAR

        val selectedDepartStation: TrainStation?
            get() = departTrainStations.find { it.isSelected }

        val selectedArriveStation: TrainStation?
            get() = arriveTrainStations.find { it.isSelected }

        val selectedDepartTrainInfo: TrainInfo?
            get() = departTrainInfos.find { it.isSelected }

        val selectedReturnTrainInfo: TrainInfo?
            get() = returnTrainInfos.find { it.isSelected}
    }
}

enum class StationType { DEPART, ARRIVE }