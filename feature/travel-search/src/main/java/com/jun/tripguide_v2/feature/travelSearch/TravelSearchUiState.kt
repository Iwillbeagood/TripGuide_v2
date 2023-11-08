package com.jun.tripguide_v2.feature.travelSearch

import com.jun.tripguide_v2.core.model.Tourist

sealed interface TravelSearchUiState {

    object Loading : TravelSearchUiState

    data class Success(
        val travelId: String = "",
        val tourists: List<Tourist> = emptyList()
    ): TravelSearchUiState {
        val selectedTourists : List<Tourist>
            get() = tourists.filter { it.isSelected }
    }
}