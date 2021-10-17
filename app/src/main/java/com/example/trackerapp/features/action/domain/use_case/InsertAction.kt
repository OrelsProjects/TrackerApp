package com.example.trackerapp.features.action.domain.use_case

import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.model.InvalidActionException
import com.example.trackerapp.features.action.domain.repository.ActionRepository

class InsertAction(
    private val repository: ActionRepository
) {
    suspend operator fun invoke(action: Action) {
        if (action.title.isBlank()) {
            throw InvalidActionException("The title cannot be blank.")
        }
        if(action.description.isBlank()){
            throw InvalidActionException("The description cannot be blank.")
        }
        return repository.insertAction(action = action)
    }
}