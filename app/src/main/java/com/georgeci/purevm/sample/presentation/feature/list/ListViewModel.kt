package com.georgeci.purevm.sample.presentation.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgeci.purevm.sample.domain.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) : ViewModel() {

    val _state = MutableStateFlow<ListState>(ListState.Loading)
    val state: StateFlow<ListState>
        get() = _state

    init {
        viewModelScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                // Trigger the flow and start listening for values.
//                // This happens when lifecycle is STARTED and stops
//                // collecting when the lifecycle is STOPPED
//                viewModel.someDataFlow.collect {
//                    // Process item
//                }
//            }
            val result = currencyRepository.getCurrencyList()
            val stateData = result.fold(
                ifLeft = { ListState.Error },
                ifRight = { items ->
                    ListState.Loaded(items)
                },
            )
            _state.value = stateData
        }
    }
}