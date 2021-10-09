package com.example.trackerapp.features.action.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackerapp.features.action.domain.model.Action

@Database(
    entities = [Action::class],
    version = 1
)
abstract class ActionDatabase: RoomDatabase() {

    abstract val actionDao: ActionDao

    companion object {
        const val DATABASE_NAME = "actions_db"
    }
}