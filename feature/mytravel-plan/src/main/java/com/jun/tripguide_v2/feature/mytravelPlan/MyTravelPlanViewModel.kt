package com.jun.tripguide_v2.feature.mytravelPlan

import androidx.lifecycle.ViewModel
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelRouteUsecase
import com.jun.tripguide_v2.core.model.Travel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyTravelPlanViewModel @Inject constructor(
    private val getTravelRouteUsecase: GetTravelRouteUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyTravelPlanUiState>(MyTravelPlanUiState.Loading)
    val uiState: MutableStateFlow<MyTravelPlanUiState> = _uiState
}

/**
 * 1. 일단 이 곳에선 가장 먼저 travelId와 동일한 route를 전부 다 가져온다.
 * 2. 그 다음 첫번쨰와 마지막에  travel의 시작 주소를 넣는다.
 * 3. 그 다음 모든 주소에 해당하는 plus code를 구글로부더 가져온다.
 * 4. 그 다음 구글에 경유지 길찾기를 진행한다.
 * 5. 진행한 결과를 가져온다. <- usecase에서 반환해야 하는 부분
 *
 *
*/


sealed interface MyTravelPlanUiState {

    object Loading : MyTravelPlanUiState

    data class Travels(
        val travels: List<Travel> = emptyList()
    ) : MyTravelPlanUiState
}