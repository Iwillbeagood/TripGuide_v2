package com.jun.tripguide_v2.feature.travelAddDialog

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetKeywordListUsecase
import com.jun.tripguide_v2.core.domain.usecase.tourapi.GetTouristsUsecase
import com.jun.tripguide_v2.core.model.Arrange
import com.jun.tripguide_v2.core.model.ContentType
import com.jun.tripguide_v2.core.model.FilterValue
import com.jun.tripguide_v2.core.model.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristAddViewModel @Inject constructor(
    private val getTouristsUsecase: GetTouristsUsecase,
    private val getKeywordListUsecase: GetKeywordListUsecase,
) : ViewModel() {

    private val _effectState = MutableSharedFlow<TravelAddDialogUiEffect>()
    val effectState: SharedFlow<TravelAddDialogUiEffect> get() = _effectState.asSharedFlow()

    var tab = MutableStateFlow(TravelAddTabs.Recommend)
        private set

    var keyword = MutableStateFlow("")
        private set

    private var pageNo = MutableStateFlow(0)

    private val _filterState = MutableStateFlow(FilterState())
    val filterState: StateFlow<FilterState> get() = _filterState

    var recommendTourists = MutableStateFlow<List<Tourist>>(emptyList())
        private set

    private val _selectedTourists = MutableStateFlow<List<Tourist>>(emptyList())
    val selectedTourists: StateFlow<List<Tourist>> get() = _selectedTourists

    val searchTourists: StateFlow<List<Tourist>>
        get() = combine(
            keyword,
            selectedTourists
        ) { keyword, selectedTourists ->
            if (keyword.isBlank()) emptyList() else getKeywordListUsecase(keyword).filter {
                it !in selectedTourists
            }
        }.catch {
            _effectState.emit(TravelAddDialogUiEffect.ShowErrorSnackBar(it))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )


    private var contentJob: Job? = null

    fun initViewModel(travelId: String) {
        viewModelScope.launch {
            combine(
                pageNo,
                filterState,
                selectedTourists
            ) { page, filter, selectedList ->
                getTouristsUsecase(
                    travelId = travelId,
                    pageNo = page,
                    arrange = filter.selectedArrange.value,
                    contentType = filter.selectedContentType.value
                ).filter {
                    it.id !in selectedList.map { it.id }
                }
            }.collectLatest { tourists ->
                recommendTourists.update {
                    tourists
                }
            }
        }
    }

    fun nextPage() {
        pageNo.update { it + 1 }
    }

    fun selectedTourist(tourist: Tourist) {
        viewModelScope.launch {
            _selectedTourists.update { currentList ->
                if (tourist in currentList) {
                    currentList - tourist
                } else {
                    currentList + tourist
                }
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

    fun changeKeyword(newValue: String) {
        keyword.update {
            newValue
        }
    }

    fun changeTab(index: Int) {
        tab.update {
            TravelAddTabs.entries.first { it.index == index }
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

@Stable
sealed interface TravelAddDialogUiEffect {

    @Immutable
    data class ShowErrorSnackBar(val throwable: Throwable) : TravelAddDialogUiEffect

    @Immutable
    data class TravelRecommendComplete(val tourists: List<Tourist>) : TravelAddDialogUiEffect
}