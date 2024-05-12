package com.jun.tripguide_v2.feature.travelInit

import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.MeansItem
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.core.model.StartingPoint
import com.jun.tripguide_v2.feature.travelInit.util.toStringType
import com.jun.tripguide_v2.feature.travel_init.R

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