package com.jun.tripguide_v2.feature.travelRecommend

import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.SortBy
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.core.model.TouristType
import com.jun.tripguide_v2.core.model.Travel

sealed interface TravelRecommendUiState {

    object Loading : TravelRecommendUiState

    data class Success(
        val pageNo: Int = 1,
        val travel: Travel,
        val touristList: List<Tourist>,
        val sortByList : List<FilterValue> = SortBy.getValues(),
        val touristTypeList : List<FilterValue> = TouristType.getValues(),
        val dialogVisibility: Boolean = false
    ): TravelRecommendUiState {
        val selectedTourist : List<Tourist>
            get() = touristList.filter { it.isSelected }
    }
}