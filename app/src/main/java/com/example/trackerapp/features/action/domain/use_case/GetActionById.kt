package com.example.trackerapp.features.action.domain.use_case

import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.repository.ActionRepository

class GetActionById(
    private val repository: ActionRepository
) {
    suspend operator fun invoke(actionId: String): Action? {
        return repository.getActionById(actionID = actionId)
    }
}