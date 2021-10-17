package com.example.trackerapp.features.action.data.repository

import com.example.trackerapp.features.action.data.data_source.ActionDao
import com.example.trackerapp.features.action.domain.model.Action
import com.example.trackerapp.features.action.domain.repository.ActionRepository
import kotlinx.coroutines.flow.Flow

class ActionRepositoryImpl(
    private val dao: ActionDao
) : ActionRepository {
    override fun getActions(): Flow<List<Action>> {
        return dao.getActions()
    }

    override fun getActions1(): List<Action> {
        return dao.getActions1()
    }

    override suspend fun getActionById(actionID: String): Action? {
        return dao.getActionById(actionID)
    }

    override suspend fun insertAction(action: Action) {
        dao.insertAction(action)
    }

    override suspend fun deleteAction(action: Action) {
        dao.deleteAction(action)
    }
}