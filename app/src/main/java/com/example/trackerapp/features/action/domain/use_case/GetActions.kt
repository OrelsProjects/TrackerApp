package com.example.trackerapp.features.action.domain.use_case

import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.repository.ActionRepository
import com.example.trackerapp.features.action.domain.util.ActionOrder
import com.example.trackerapp.features.action.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetActions(
    private val repository: ActionRepository
) {
    operator fun invoke(
        actionOrder: ActionOrder = ActionOrder.Date(OrderType.Descending)
    ): Flow<List<Action>> {
        return repository.getActions().map { actions ->
            when(actionOrder.orderType) {
                is OrderType.Ascending -> {
                    when(actionOrder) {
                        is ActionOrder.Title -> actions.sortedBy { it.title.lowercase() }
                        is ActionOrder.Date -> actions.sortedBy { it.timeCreatedInMillis }
                        is ActionOrder.Color -> actions.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(actionOrder) {
                        is ActionOrder.Title -> actions.sortedByDescending { it.title.lowercase() }
                        is ActionOrder.Date -> actions.sortedByDescending { it.timeCreatedInMillis }
                        is ActionOrder.Color -> actions.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}