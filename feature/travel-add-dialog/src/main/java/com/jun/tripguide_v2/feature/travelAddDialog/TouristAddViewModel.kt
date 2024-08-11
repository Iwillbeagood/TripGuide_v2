package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetTouristsUsecase
import com.jun.tripguide_v2.core.model.Arrange
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.DestinationCode
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristAddViewModel @Inject constructor(
    private val getTouristsUsecase: GetTouristsUsecase
) : ViewModel() {

    private val _touristAddEffect = MutableSharedFlow<TravelAddDialogUiEffect>()
    val touristAddEffect: SharedFlow<TravelAddDialogUiEffect> get() = _touristAddEffect.asSharedFlow()

    private val _filterState = MutableStateFlow(FilterState())
    val filterState: StateFlow<FilterState> get() = _filterState

    private val _touristsState = MutableStateFlow(TouristState())
    val touristsState: StateFlow<TouristState> get() = _touristsState

    var selectedTourists = MutableStateFlow<List<Tourist>>(emptyList())
        private set

    fun initViewModel(destinationCode: DestinationCode) {
        viewModelScope.launch {
            combine(
                selectedTourists,
                filterState
            ) { searchTourists, filter ->
                getTouristsUsecase(
                    destinationCode = destinationCode,
                    arrange = filter.selectedArrange.value,
                    contentType = filter.selectedContentType.value
                ).filter { tourists ->
                    searchTourists.none { it.id == tourists.id }
                }
            }.collectLatest { tourists ->
                _touristsState.update {
                    it.copy(
                        tourists = tourists
                    )
                }
            }
        }
    }

    fun selectedTourist(tourist: Tourist) {
        viewModelScope.launch {
            selectedTourists.update {
                it + tourist
            }
        }
    }

    fun unselectedTourist(tourist: Tourist) {
        viewModelScope.launch {
            selectedTourists.update {
                it - tourist
            }
        }
    }

    fun changeArrange(value: FilterValue) {
        viewModelScope.launch {
            _filterState.update {
                it.copy(
                    arranges = it.arranges.map {
                        it.copy(
                            isSelected = it == value
                        )
                    }
                )
            }
        }
    }

    fun changeContentType(value: FilterValue) {
        viewModelScope.launch {
            _filterState.update {
                it.copy(
                    contentTypes = it.contentTypes.map {
                        it.copy(
                            isSelected = it == value
                        )
                    }
                )
            }
        }
    }

    fun touristAddComplete() {
        viewModelScope.launch {
            _touristAddEffect.emit(TravelAddDialogUiEffect.TravelRecommendComplete(selectedTourists.value))
        }
    }
}

@Immutable
data class FilterState(
    val arranges: List<FilterValue> = Arrange.getValues(),
    val contentTypes: List<FilterValue> = ContentType.getValues(),
) {
    val selectedArrange: FilterValue
        get() = arranges.first { it.isSelected }

    val selectedContentType: FilterValue
        get() = contentTypes.first { it.isSelected }
}

@Immutable
data class TouristState(
    val tourists: List<Tourist> = emptyList()
)

@Stable
sealed interface TravelAddDialogUiEffect {

    @Immutable
    data class ShowErrorSnackBar(val throwable: Throwable) : TravelAddDialogUiEffect

    @Immutable
    data class TravelRecommendComplete(val tourists: List<Tourist>) : TravelAddDialogUiEffect
}
