package com.jun.tripguide_v2.feature.travelSearch

import com.jun.tripguide_v2.core.model.Tourist

sealed interface TravelSearchUiState {

    object Loading : TravelSearchUiState

    data class TouristList(
        val pageNo: Int = 1,
        val touristList: List<Tourist> = emptyList()
    ): TravelSearchUiState {
        val selectedTourist : List<Tourist>
            get() = touristList.filter { it.isSelected }
    }
}