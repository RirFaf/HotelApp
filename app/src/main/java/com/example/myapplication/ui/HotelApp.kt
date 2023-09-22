package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.AppNavGraph
import com.example.myapplication.viewmodels.HotelViewModel
import com.example.myapplication.viewmodels.RoomsViewModel

@Composable
fun HotelApp(
) {
    val hotelViewModel: HotelViewModel = viewModel(factory = HotelViewModel.Factory)
    val roomsViewModel: RoomsViewModel = viewModel(factory = RoomsViewModel.Factory)
    val navController = rememberNavController()
    AppNavGraph(navController = navController, hotelViewModel = hotelViewModel, roomsViewModel = roomsViewModel)
}
