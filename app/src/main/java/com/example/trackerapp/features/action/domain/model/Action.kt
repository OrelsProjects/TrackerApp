package com.example.trackerapp.features.action.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trackerapp.ui.theme.*
import java.util.*

@Entity
data class Action(
    val title: String,
    val description: String,
    val color: Int,
    val timeCreatedInMillis: Long = Calendar.getInstance().timeInMillis,
    @PrimaryKey val id: String = ""
) {
    companion object {
        val actionColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
    class InvalidActionException(message: String): Exception(message)
}