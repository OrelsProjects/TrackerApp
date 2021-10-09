package com.example.trackerapp.features.action.presentation.add_edit_action

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.use_case.ActionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditActionViewModel @Inject constructor(
    private val actionUseCases: ActionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _actionColor = mutableStateOf(Action.actionColors.random().toArgb())
    val actionColor: State<Int> = _actionColor

    private val _actionTitle = mutableStateOf(
        ActionTextFieldState(
            hint = "Enter a title... "
        )
    )
    val actionTitle: State<ActionTextFieldState> = _actionTitle

    private val _actionDescription = mutableStateOf(
        ActionTextFieldState(
            hint = "Description"
        )
    )
    val actionDescription: State<ActionTextFieldState> = _actionDescription

    private var currentActionId: String = ""

    init {
        savedStateHandle.get<String>("actionId")?.let { actionId ->
            if (actionId != "") {
                viewModelScope.launch {
                    actionUseCases.getActionById(actionId)?.also { action ->
                        currentActionId = action.id
                        _actionTitle.value = actionTitle.value.copy(
                            text = action.title,
                            isHintVisible = false
                        )
                        _actionDescription.value = _actionDescription.value.copy(
                            text = action.description,
                            isHintVisible = false
                        )
                        _actionColor.value = action.color
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditActionEvent) {
        when (event) {
            is AddEditActionEvent.ChangeColor -> {
                _actionColor.value = event.color
            }
            is AddEditActionEvent.EnteredDescription -> {
                _actionDescription.value = actionDescription.value.copy(
                    text = event.value,
                    isHintVisible = event.value.isBlank()
                )
            }

            is AddEditActionEvent.EnteredTitle -> {
                _actionTitle.value = actionTitle.value.copy(
                    text = event.value,
                    isHintVisible = event.value.isBlank()
                )
            }
            is AddEditActionEvent.ChangeDescriptionFocus -> {
                _actionDescription.value = actionDescription.value.copy(
                    isHintVisible = !event.focusState.hasFocus
                            && actionDescription.value.text.isBlank()
                )
            }
            is AddEditActionEvent.ChangeTitleFocus -> {
                _actionTitle.value = actionTitle.value.copy(
                    isHintVisible = !event.focusState.hasFocus
                            && actionTitle.value.text.isBlank()
                )
            }
            is AddEditActionEvent.SaveAction -> {
                viewModelScope.launch {
                    try {
                        actionUseCases.insertAction(
                            Action(
                                title = actionTitle.value.text,
                                description = actionTitle.value.text,
                                color = actionColor.value,
                                id = currentActionId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveAction)
                    } catch (exception: Action.InvalidActionException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = exception.message ?: "Couldn't save the action"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveAction : UiEvent()
    }
}