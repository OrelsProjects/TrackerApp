package com.example.trackerapp.features.action.presentation.utils

sealed class Screen(val route: String) {
    object ActionsScreen: Screen("actions_screen")
    object AddEditActionScreen: Screen("add_edit_action_screen")
}