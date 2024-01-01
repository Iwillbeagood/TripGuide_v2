package com.jun.tripguide_v2.feature.recommend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetFestivalUsecase
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetTouristByCurLocationUsecase
import com.jun.tripguide_v2.core.model.tourApi.Festival
import com.jun.tripguide_v2.core.model.tourApi.LocationBasedTourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val getTouristByCurLocationUsecase: GetTouristByCurLocationUsecase,
    private val getFestivalUsecase: GetFestivalUsecase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _uiState = MutableStateFlow<RecommendUiState>(RecommendUiState.Loading)
    val uiState: StateFlow<RecommendUiState> get() = _uiState

    private var contentJob: Job? = null

    fun fetchCurrentLocation() {
        if (contentJob != null) {
            contentJob?.cancel()
        }

        contentJob = viewModelScope.launch {
            combine(
                uiState,
                getTouristByCurLocationUsecase(),
                flow { emit(getFestivalUsecase()) },
            ) { uiState, locationBasedTourists, festivals ->
                when(uiState) {
                    RecommendUiState.Loading -> {
                        RecommendUiState.Success(
                            locationBasedTourists = locationBasedTourists,
                            festivals = festivals
                        )
                    }
                    else -> uiState
                }
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun fetchNextPageLocationBasedTourist() {

    }

    fun fetchNextPageStay() {

    }

    fun onFestivalItemClick() {

    }

    fun stopUpdates() {
        contentJob?.cancel()
    }

    fun revokedPermissions() {

    }
}

sealed interface RecommendUiState {

    object Loading : RecommendUiState

    data class Success(
        val locationBasedTourists: List<LocationBasedTourist>,
        val locationBasedTouristsPageNo: Int = 1,
        val festivals: List<Festival>,
        val festivalsPageNo: Int = 1
    ) : RecommendUiState

    object RevokedPermissions : RecommendUiState
}