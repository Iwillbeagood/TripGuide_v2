package com.jun.tripguide_v2.feature.mytravelPlan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.model.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.burnoutcrew.reorderable.ItemPosition
import java.time.Duration
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class MyTravelPlanViewModel @Inject constructor(
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<MyTravelPlanUiState>(MyTravelPlanUiState.Loading)
    val uiState: StateFlow<MyTravelPlanUiState> get() = _uiState

    private val _uiEffect = MutableStateFlow<MyTravelPlanUiEffect>(MyTravelPlanUiEffect.Idle)
    val uiEffect: StateFlow<MyTravelPlanUiEffect> get() = _uiEffect

    private var contentJob: Job? = null

    fun fetchOrderedTravelRoute(travelId: String) {

    }

    private fun getDurationOfRoutes(routes: List<Route>): Duration {
        val origin = routes.find { it.isSelected } ?: routes[0]
        val destination = routes.find { it.isBeforeRouteSelected } ?: routes[1]
        val destinationTime = if (origin == routes.first() || destination == routes.last()) {
            destination.time
        } else {
            destination.time.minusHours(2)
        }

        return Duration.between(origin.time, destinationTime)
    }

    fun selectRouteItem(selectedOrderNum: Int) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return
        if (uiState.routes.lastIndex == selectedOrderNum) return

        contentJob = viewModelScope.launch {
            val newRoutes = uiState.routes.map {
                it.copy(
                    isSelected = it.orderNum == selectedOrderNum,
                    isBeforeRouteSelected = it.orderNum == selectedOrderNum + 1
                )
            }

            _uiState.value = uiState.copy(
                routes = newRoutes, duration = getDurationOfRoutes(newRoutes)
            )
        }
    }

    fun travelDaysItemPicked(day: Int) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(
                travelDays = uiState.travelDays.map {
                    it.copy(
                        isSelected = it.day == day
                    )
                }, nowDay = day, isEditMode = false
            )
            _uiEffect.value = MyTravelPlanUiEffect.Idle
        }

        selectRouteItem(uiState.routes.find { it.day == day }?.orderNum ?: 0)
    }

    fun onMoveRouteItem(from: ItemPosition, to: ItemPosition) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(routes = uiState.routes.toMutableList().apply {
                add(to.index, removeAt(from.index))
            })
        }
    }

    fun isDragEnabled(draggedOver: ItemPosition, dragging: ItemPosition): Boolean {
        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return false

        return uiState.routes.getOrNull(draggedOver.index)?.orderNum in (1 until uiState.routes.lastIndex)
    }

    private fun isRoutesChanged(): Boolean {
        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return false

        if (uiState.routes.size != uiState.originRoutes.size) return true

        for ((index, routes) in uiState.routes.withIndex()) {
            if (uiState.originRoutes[index].orderNum != routes.orderNum) return true
        }

        return false
    }

    fun changeEditMode() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value =
                uiState.copy(isEditMode = !uiState.isEditMode, routes = uiState.routes.map {
                    it.copy(
                        isSelected = false, isBeforeRouteSelected = false
                    )
                })
        }
    }

    fun showEditConfirmationDialog() {
        if (!isRoutesChanged()) return

        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            _uiEffect.value = MyTravelPlanUiEffect.ShowEditConfirmationDialog
        }
    }

    fun editDialogConfirmation() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return

        contentJob = viewModelScope.launch {
//            val updateRoutes = setTimeUsecase(startTime = uiState.travel.startDate,
//                routes = uiState.routes.mapIndexed { index, route ->
//                    route.copy(orderNum = index)
//                })
//
//            _uiState.value = uiState.copy(
//                isEditMode = false, routes = updateRoutes
//            )
//            initAndInsertRouteUsecase(uiState.travel.travelId, updateRoutes)
//            _uiEffect.value = MyTravelPlanUiEffect.Idle
        }
    }

    fun editDialogDismiss() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return

        contentJob = viewModelScope.launch {
            val originRoutes = uiState.originRoutes.sortedBy { it.orderNum }
            _uiState.value = uiState.copy(
                isEditMode = false, routes = originRoutes
            )
//            initAndInsertRouteUsecase(uiState.travel.travelId, originRoutes)
            _uiEffect.value = MyTravelPlanUiEffect.Idle
        }
    }

    fun deleteRoute(route: Route) {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        val uiState = uiState.value

        if (uiState !is MyTravelPlanUiState.Success) return

        contentJob = viewModelScope.launch {
            _uiState.value = uiState.copy(routes = uiState.routes.toMutableList().apply {
                removeIf { it.orderNum == route.orderNum }
            })
            _uiEffect.value = MyTravelPlanUiEffect.Idle
        }
    }

    fun changeRoutesMapState() {
        _uiEffect.value = when(uiEffect.value) {
            MyTravelPlanUiEffect.Idle -> MyTravelPlanUiEffect.ShowRoutesMap
            MyTravelPlanUiEffect.ShowEditConfirmationDialog -> MyTravelPlanUiEffect.ShowRoutesMap
            MyTravelPlanUiEffect.ShowRoutesMap -> MyTravelPlanUiEffect.Idle
        }
    }
}


