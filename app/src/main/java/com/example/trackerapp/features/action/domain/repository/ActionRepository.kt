package com.example.trackerapp.features.action.domain.repository

import com.example.trackerapp.features.action.domain.model.Action
import kotlinx.coroutines.flow.Flow

interface ActionRepository {
    fun getActions(): Flow<List<Action>>
    fun getActions1(): List<Action>
    suspend fun getActionById(actionID: String): Action?
    suspend fun insertAction(action: Action)
    suspend fun deleteAction(action: Action)
}