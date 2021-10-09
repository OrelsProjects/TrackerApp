package com.example.trackerapp.features.action.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
