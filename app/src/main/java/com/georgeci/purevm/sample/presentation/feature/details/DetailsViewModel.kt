package com.georgeci.purevm.sample.presentation.feature.details

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgeci.purevm.sample.domain.repository.CurrencyRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//@Stable
@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted("name") private val name: String,
    @Assisted("code") private val code: String,
    private val currencyRepository: CurrencyRepository,
) : ViewModel() {
    fun click() {
        "".toString()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(1000)
            }
            state2 = state2.copy(value = state2.value + 1)
//            state2 = state2.copy(value = 5)
        }
    }

    fun click2() {
        state3 += 1
    }

    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading(code, name))

    val state: StateFlow<DetailsState>
        get() = _state
//    private val _stateate2 = MutableStateFlow<Int>(0)

    var state2 by mutableStateOf<Ps>(Ps(0))
    var state3 by mutableStateOf<Int>(0)

    init {
        viewModelScope.launch {
            val result = currencyRepository.getCurrencyExchangeRates(code)
            val stateData = result.fold(
                ifLeft = { DetailsState.Error(code, name) },
                ifRight = { items ->
                    DetailsState.Loaded(code, name, items)
                },
            )
            _state.value = stateData
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("name") name: String,
            @Assisted("code") code: String,
        ): DetailsViewModel
    }
}

@Stable
data class Ps(
    val value: Int,
    val list: List<String> = listOf(),
)