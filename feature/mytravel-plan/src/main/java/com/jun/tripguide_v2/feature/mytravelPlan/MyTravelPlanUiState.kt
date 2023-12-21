package com.jun.tripguide_v2.feature.mytravelPlan

import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.core.model.Travel
import com.jun.tripguide_v2.core.model.TravelDay
import com.jun.tripguide_v2.feature.mytravelPlan.util.getFormattedDate
import java.time.Duration

sealed interface MyTravelPlanUiState {

    object Loading : MyTravelPlanUiState

    data class Success(
        val travel: Travel,
        val originRoutes: List<Route>,
        val routes: List<Route>,
        val duration: Duration,
        val nowDay: Int = 1,
        val isEditMode: Boolean = false,
        val travelDays: List<TravelDay> = getFormattedDate(travel.startDate, travel.endDate)
    ) : MyTravelPlanUiState {

        val dayRoutes: List<Route>
            get() = routes.filter { it.day == nowDay }

        val selectedRoute: Route
            get() = routes.find { it.isSelected } ?: routes.first()
    }
}