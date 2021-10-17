package com.example.trackerapp.features.action.presentation.actions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackerapp.features.action.domain.use_case.ActionUseCases
import com.example.trackerapp.features.action.domain.util.ActionOrder
import com.example.trackerapp.features.action.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val actionUseCases: ActionUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ActionsState())
    val state: State<ActionsState> = _state

    private var getActionsJob: Job? = null

    init {
        getActions()
    }

    private fun getActions(actionOrder: ActionOrder = ActionOrder.Date(OrderType.Descending)) {
        getActionsJob?.cancel()
        getActionsJob = actionUseCases.getActions(actionOrder = actionOrder)
            .onEach { actions ->
                _state.value = state.value.copy(
                    actions = actions,
                    actionOrder = actionOrder
                )
                println()
            }
            .launchIn(viewModelScope)
    }

}