package com.example.trackerapp.features.action.domain.util

sealed class ActionOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : ActionOrder(orderType)
    class Date(orderType: OrderType) : ActionOrder(orderType)
    class Color(orderType: OrderType) : ActionOrder(orderType)

    fun copy(orderType: OrderType): ActionOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}