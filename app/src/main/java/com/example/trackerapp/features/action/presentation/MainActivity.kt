package com.example.trackerapp.features.action.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trackerapp.features.action.presentation.actions.ActionsScreen
import com.example.trackerapp.features.action.presentation.add_edit_action.AddEditActionScreen
import com.example.trackerapp.features.action.presentation.utils.Screen
import com.example.trackerapp.ui.theme.TrackerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ActionsScreen.route
                    ) {
                        composable(route = Screen.ActionsScreen.route) {
                            ActionsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditActionScreen.route
                                    +
                                    "?actionId={actionId}&actionColor={actionColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "actionId"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = "actionColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("actionColor") ?: -1
                            AddEditActionScreen(
                                navController = navController,
                                actionColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrackerAppTheme {
        Greeting("Android")
    }
}