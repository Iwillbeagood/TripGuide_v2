package com.jun.tripguide_v2.feature.addtravel

import com.jun.tripguide_v2.core.model.AreaCode
import com.jun.tripguide_v2.core.model.MeansItems
import com.jun.tripguide_v2.core.model.MeansType

sealed interface AddTravelUiState {

    object Loading : AddTravelUiState

    data class PickDestination(
        val isPickDestinationEntered: Boolean = false,
        val defaultAreaCodes: List<AreaCode> = listOf(),
        val areaCodes: List<AreaCode>? = null
    ) : AddTravelUiState

    data class MeansItemState(
        val meansItems: List<MeansItems> = listOf(
            MeansItems(R.drawable.ic_car, MeansType.CAR),
            MeansItems(R.drawable.ic_bus, MeansType.PUBLIC_TRANS),
            MeansItems(R.drawable.ic_plane, MeansType.PLANE),
            MeansItems(R.drawable.ic_train, MeansType.TRAIN)
        )
    ): AddTravelUiState
}
