package com.jun.tripguide_v2.feature.addtravel.pickdestination

import com.jun.tripguide_v2.core.model.AreaCode

sealed interface PickDestinationUiState {

    object Loading: PickDestinationUiState

    data class AreaCodes(val defaultAreaCodes: List<AreaCode>, val areaCodes: List<AreaCode>? = null) : PickDestinationUiState
}