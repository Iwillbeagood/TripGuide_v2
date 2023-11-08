package com.jun.tripguide_v2.feature.travelRecommend

import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Arrange
import com.jun.tripguide_v2.core.model.Tourist
import com.jun.tripguide_v2.core.model.ContentType

sealed interface TravelRecommendUiState {

    object Loading : TravelRecommendUiState

    data class Success(
        val travelId: String = "",
        val pageNo: Int = 1,
        val tourists: List<Tourist>,
        val arrangeTypes : List<FilterValue> = Arrange.getValues(),
        val contentTypes : List<FilterValue> = ContentType.getValues(),
        val dialogVisibility: Boolean = false
    ): TravelRecommendUiState {
        val selectedTourists : List<Tourist>
            get() = tourists.filter { it.isSelected }

        val selectedArrange : FilterValue
            get() = arrangeTypes.find { it.isSelected }!!

        val selectedContent: FilterValue
            get() = contentTypes.find { it.isSelected }!!
    }
}