package com.jun.tripguide_v2.feature.mytravel

import com.jun.tripguide_v2.core.model.Travel

sealed interface MyTravelUiState {

    object Loading : MyTravelUiState

    object Empty : MyTravelUiState

    data class Travels(
        val travels: List<Travel> = emptyList()
    ): MyTravelUiState {

        val previousTravels : List<Travel>
            get() = travels.filter { it.endDate < currentMillis }

        val currentTravels : List<Travel>
            get() = travels.filter { currentMillis in it.startDate..it.endDate }

        val planedTravels : List<Travel>
            get() = travels.filter { it.startDate > currentMillis }


        companion object {
            private val currentMillis = System.currentTimeMillis()
        }
    }
}