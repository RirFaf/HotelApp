package com.example.myapplication.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.SelectedRoomModel
import com.example.myapplication.data.SelectedRoomRepository

sealed interface BookingUIState {
    data class Success(val selectedRoom: SelectedRoomModel):BookingUIState
    object Error : BookingUIState
    object Loading : BookingUIState
}

class BookingViewModel(
    private val selectedRoomRepository: SelectedRoomRepository
) : ViewModel() {
    var bookingUIState: BookingUIState by mutableStateOf(BookingUIState.Loading)
        private set


}