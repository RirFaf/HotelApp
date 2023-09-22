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
import com.example.myapplication.Rooms
import com.example.myapplication.data.RoomsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface RoomsUIState {
    data class Success(val rooms: Rooms) : RoomsUIState
    object Error : RoomsUIState
    object Loading : RoomsUIState
}

class RoomsViewModel(
    private val roomsRepository: RoomsRepository
) : ViewModel() {

    var roomsUIState: RoomsUIState by mutableStateOf(RoomsUIState.Loading)
        private set

    init{
        getRooms()
    }

    fun getRooms(){
        viewModelScope.launch {
            roomsUIState = RoomsUIState.Loading
            roomsUIState = try {
                RoomsUIState.Success(roomsRepository.getRooms())
            } catch (e: IOException){
                RoomsUIState.Error
            } catch (e: HttpException){
                RoomsUIState.Error
            }
        }
    }

    //Для извлечения репозитория и подвязки его к приложению
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HotelApplication)
                val roomsRepository = application.container.roomsRepository
                RoomsViewModel(roomsRepository = roomsRepository)
            }
        }
    }
}