package com.example.trackerapp.di

import android.app.Application
import androidx.room.Room
import com.example.trackerapp.features.action.data.data_source.ActionDatabase
import com.example.trackerapp.features.action.data.repository.ActionRepositoryImpl
import com.example.trackerapp.features.action.domain.repository.ActionRepository
import com.example.trackerapp.features.action.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideActionDatabase(app:Application): ActionDatabase{
        return Room.databaseBuilder(
            app,
            ActionDatabase::class.java,
            ActionDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideActionRepository(db: ActionDatabase): ActionRepository{
        return ActionRepositoryImpl(db.actionDao)
    }

    @Provides
    @Singleton
    fun provideActionUseCases(repository: ActionRepository): ActionUseCases{
        return ActionUseCases(
            getActions = GetActions(repository),
            getActionById = GetActionById(repository),
            insertAction = InsertAction(repository),
            deleteAction = DeleteAction(repository)
        )
    }
}