package com.jun.tripguide_v2.feature.travel_meansinfo.train

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.jun.tripguide_v2.core.model.TrainInfo
import com.jun.tripguide_v2.core.model.TrainStation
import com.jun.tripguide_v2.core.model.TrainType
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2.feature.travel_meansinfo.utils.longToTrainDate

@Stable
sealed interface TrainUiState {

    @Immutable
    object Loading : TrainUiState

    @Immutable
    data class Success(
        val travel: Travel,
        val departDropDownExpanded: Boolean = false,
        val arriveDropDownExpanded: Boolean = false,
        val departTrainDropDownExpanded: Boolean = false,
        val returnTrainDropDownExpanded: Boolean = false,
        val departTrainStations: List<TrainStation> = emptyList(),
        val arriveTrainStations: List<TrainStation> = emptyList(),
        val trainInfos: List<TrainInfo> = emptyList(),
        val returnTrainInfos: List<TrainInfo> = emptyList(),
    ) : TrainUiState {

        val departStationTitle: String
            get() = travel.startPlace.title + "에서 출발하는 역"

        val arriveStationTitle: String
            get() = travel.destination.destinationString + "로 도착하는 역"

        val selectedDepartStation: TrainStation?
            get() = departTrainStations.find { it.isSelected }

        val selectedArriveStation: TrainStation?
            get() = arriveTrainStations.find { it.isSelected }

        val isStationSelected
            get() = selectedDepartStation != null && selectedArriveStation != null

        val trainTitle: String
            get() = if (isStationSelected) {
                longToTrainDate(travel.startDate) + " " + selectedDepartStation!!.stationName + " -> " + selectedArriveStation!!.stationName
            } else {
                ""
            }

        val returnTrainTitle: String
            get() = if (isStationSelected) {
                longToTrainDate(travel.endDate) + " " + selectedArriveStation!!.stationName + " -> " + selectedDepartStation!!.stationName
            } else {
                ""
            }

        val selectedTrainInfo: TrainInfo?
            get() = trainInfos.find { it.isSelected }

        val selectedReturnTrainInfo: TrainInfo?
            get() = returnTrainInfos.find { it.isSelected }

        val trainName
            get() = with(selectedTrainInfo) {
                if (this != null) {
                    "$trainName ($depPlanedTime ~ $arrPlanedTime)"
                } else {
                    "기차를 선택해 주세요."
                }
            }

        val returnTrainName
            get() = with(selectedReturnTrainInfo) {
                if (this != null) {
                    "$trainName ($depPlanedTime ~ $arrPlanedTime)"
                } else {
                    "기차를 선택해 주세요."
                }
            }
    }
}