package com.example.trackerapp.features.action.data.data_source

import androidx.room.*
import com.example.trackerapp.features.action.domain.model.Action
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {

    @Query("SELECT * FROM `Action`")
    fun getActions(): Flow<List<Action>>

    @Query("SELECT * FROM `Action` WHERE id=:actionID ")
    suspend fun getActionById(actionID: String): Action

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAction(action: Action)

    @Delete
    suspend fun deleteAction(action: Action)
}