package com.example.myapplication.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.HotelApplication
import com.example.myapplication.SelectedRoomModel
import com.example.myapplication.data.BookingRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookingUIState {
    data class Success(val selectedRoom: SelectedRoomModel) : BookingUIState
    object Error : BookingUIState
    object Loading : BookingUIState
}

class BookingViewModel(
    private val bookingRepository: BookingRepository
) : ViewModel() {
    var bookingUIState: BookingUIState by mutableStateOf(BookingUIState.Loading)
        private set

    init{
        getSelectedRoom()
    }
    fun getSelectedRoom() {
        viewModelScope.launch {
            bookingUIState = BookingUIState.Loading
            bookingUIState = try {
                BookingUIState.Success(bookingRepository.getSelectedRoom())
            } catch (e: IOException) {
                BookingUIState.Error
            } catch (e: HttpException) {
                BookingUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HotelApplication)
                val bookingRepository = application.container.bookingRepository
                BookingViewModel(bookingRepository = bookingRepository)
            }
        }
    }
}