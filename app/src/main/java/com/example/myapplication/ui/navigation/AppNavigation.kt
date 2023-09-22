package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.ErrorScreen
import com.example.myapplication.ui.screens.HotelScreen
import com.example.myapplication.ui.screens.LoadingScreen
import com.example.myapplication.ui.screens.RoomsScreen
import com.example.myapplication.viewmodels.HotelUIState
import com.example.myapplication.viewmodels.HotelViewModel
import com.example.myapplication.viewmodels.RoomsUIState
import com.example.myapplication.viewmodels.RoomsViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    hotelViewModel: HotelViewModel,
    roomsViewModel: RoomsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HotelScreen.route
    ) {
        lateinit var hotelName: String
        composable(Screen.HotelScreen.route) {
            when (val hotelUIState = hotelViewModel.hotelUIState) {
                is HotelUIState.Success -> {
                    HotelScreen(navController = navController, hotel = hotelUIState.hotel)
                    hotelName = hotelUIState.hotel.name.toString()
                }

                is HotelUIState.Loading -> {
                    LoadingScreen()
                }

                is HotelUIState.Error -> {
                    ErrorScreen(
                        retryAction = {
                            hotelViewModel.getHotel()
                        }
                    )
                }
            }
        }
        composable(Screen.RoomsScreen.route) {
            when (val roomsUIState = roomsViewModel.roomsUIState) {
                is RoomsUIState.Success -> {
                    RoomsScreen(
                        navController = navController,
                        rooms = roomsUIState.rooms.rooms,
                        hotelName = hotelName
                    )
                }

                is RoomsUIState.Loading -> {
                    LoadingScreen()
                }

                is RoomsUIState.Error -> {
                    ErrorScreen(
                        retryAction = {
                            roomsViewModel.getRooms()
                        }
                    )
                }
            }
        }
    }
}