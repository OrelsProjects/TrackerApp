package com.example.trackerapp.features.action.presentation.actions

import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.util.ActionOrder
import com.example.trackerapp.features.action.domain.util.OrderType

data class ActionsState(
    val actions: List<Action> = emptyList(),
    val actionOrder: ActionOrder = ActionOrder.Date(OrderType.Descending)
) {
}