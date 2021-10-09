package com.example.trackerapp.features.action.presentation.utils

sealed class Screen(val route: String) {
    object ActionScreen: Screen("actions_screne")
    object AddEditActionScreen: Screen("add_edit_action_screen")
}