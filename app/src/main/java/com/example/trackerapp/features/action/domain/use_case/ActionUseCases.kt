package com.example.trackerapp.features.action.domain.use_case

data class ActionUseCases(
    val getActions: GetActions,
    val deleteAction: DeleteAction,
    val insertAction: InsertAction,
    val getActionById: GetActionById
)