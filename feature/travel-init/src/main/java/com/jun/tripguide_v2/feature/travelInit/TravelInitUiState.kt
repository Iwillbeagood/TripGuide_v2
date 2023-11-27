package com.jun.tripguide_v2.feature.travelInit

import com.jun.tripguide_v2.core.model.DateDuration
import com.jun.tripguide_v2.core.model.MeansItems
import com.jun.tripguide_v2.core.model.MeansType
import com.jun.tripguide_v2.feature.travel_init.R
import java.time.LocalTime

sealed interface TravelInitUiState {

    object Loading : TravelInitUiState

    data class Success(
        val dateDuration: DateDuration? = null,
        val meansItems: List<MeansItems> = listOf(
            MeansItems(R.drawable.ic_car, MeansType.CAR, isSelected = true),
            MeansItems(R.drawable.ic_bus, MeansType.PUBLIC_TRANS),
            MeansItems(R.drawable.ic_plane, MeansType.PLANE),
            MeansItems(R.drawable.ic_train, MeansType.TRAIN)
        ),
        val startTime: LocalTime? = null,
    ): TravelInitUiState
}
