package com.jun.tripguide_v2.feature.mytravel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyTravelViewModel @Inject constructor(
    private val getTravelsUsecase: GetTravelsUsecase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val uiState: StateFlow<MyTravelUiState> =
        flow { emit(getTravelsUsecase()) }
            .map { travels ->
                if (travels.isNotEmpty()) {
                    MyTravelUiState.Travels(travels)
                } else {
                    MyTravelUiState.Empty
                }
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MyTravelUiState.Empty
            )
}