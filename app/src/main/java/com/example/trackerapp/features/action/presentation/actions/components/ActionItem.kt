package com.example.trackerapp.features.action.presentation.actions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.trackerapp.features.action.domain.model.Action

@Composable
fun ActionItem(
    action: Action,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary),
        ) {

        }
    }
}