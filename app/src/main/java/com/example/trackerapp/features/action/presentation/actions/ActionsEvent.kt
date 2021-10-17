package com.example.trackerapp.features.action.presentation.actions

import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.util.ActionOrder

sealed class ActionsEvent {
    data class Order(val actionOrder: ActionOrder) : ActionsEvent()
    data class DeleteAction(val action: Action) : ActionsEvent()
    object ToggleActionsOrder : ActionsEvent()
}