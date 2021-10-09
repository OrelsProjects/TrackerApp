package com.example.trackerapp.features.action.presentation.add_edit_action

import androidx.compose.ui.focus.FocusState

sealed class AddEditActionEvent {
    data class EnteredTitle(val value: String): AddEditActionEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditActionEvent()
    data class EnteredDescription(val value: String): AddEditActionEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddEditActionEvent()
    data class ChangeColor(val color: Int): AddEditActionEvent()
    object SaveAction: AddEditActionEvent()
}