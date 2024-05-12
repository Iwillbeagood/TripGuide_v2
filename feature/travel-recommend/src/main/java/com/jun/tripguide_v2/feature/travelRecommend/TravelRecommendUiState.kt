package com.jun.tripguide_v2.feature.travelRecommend

import com.jun.tripguide_v2.core.model.Arrange
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist

sealed interface TravelRecommendUiState {

    object Loading : TravelRecommendUiState

    data class Success(
        val travelId: String = "",
        val pageNo: Int = 1,
        val tourists: List<Tourist>,
        val selectedTourists: Set<Tourist> = emptySet(),
        val arrangeTypes: List<FilterValue> = Arrange.getValues(),
        val contentTypes: List<FilterValue> = ContentType.getValues(),
        val dialogVisibility: Boolean = false
    ) : TravelRecommendUiState {

        val selectedArrange: FilterValue
            get() = arrangeTypes.find { it.isSelected }!!

        val selectedContent: FilterValue
            get() = contentTypes.find { it.isSelected }!!
    }
}