package com.example.myapplication.ui.navigation

sealed class Screen (val route: String) {
    object HotelScreen: Screen ("hotel_screen")
    object RoomsScreen: Screen ("rooms_screen")
    object BookingScreen: Screen ("booking_screen")
    object DoneScreen: Screen ("done_screen")
}