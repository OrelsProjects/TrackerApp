package com.example.trackerapp.features.action.presentation.actions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trackerapp.features.action.presentation.actions.components.ActionItem
import com.example.trackerapp.features.action.presentation.utils.Screen

@Composable
fun ActionsScreen(
    navController: NavController,
    viewModel: ActionsViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value
    Scaffold(
        floatingActionButton =
        {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditActionScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }, scaffoldState = scaffoldState
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.actions) { action ->
                ActionItem(
                    action = action,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.AddEditActionScreen.route
                                        + "actionId=${action.actionId}&actionColor=${action.color}"
                            )
                        }
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewActionsScreen() {
    ActionsScreen(rememberNavController())
}