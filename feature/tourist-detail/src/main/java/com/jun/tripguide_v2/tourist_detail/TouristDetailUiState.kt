package com.jun.tripguide_v2.tourist_detail

import com.jun.tripguide_v2.core.model.tourApi.CommonInfo
import com.jun.tripguide_v2.core.model.tourApi.DetailIntro

sealed interface TouristDetailUiState {

    object Loading : TouristDetailUiState

    data class Success(val commonInfo: CommonInfo, val detailIntros: List<DetailIntro>) : TouristDetailUiState
}