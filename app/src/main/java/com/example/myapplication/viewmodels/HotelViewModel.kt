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
import com.example.myapplication.data.HotelRepository
import com.example.myapplication.network.models.HotelModel
import com.example.myapplication.HotelApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface HotelUIState {
    data class Success(val hotel: HotelModel) : HotelUIState
    object Error : HotelUIState
    object Loading : HotelUIState
}

class HotelViewModel(
    private val hotelRepository: HotelRepository
) : ViewModel() {

    var hotelUIState: HotelUIState by mutableStateOf(HotelUIState.Loading)
        private set

    init{
        getHotel()
    }

    fun getHotel(){
        viewModelScope.launch {
            hotelUIState = HotelUIState.Loading
            hotelUIState = try {
                HotelUIState.Success(hotelRepository.getHotel())
            } catch (e: IOException){
                HotelUIState.Error
            } catch (e: HttpException){
                HotelUIState.Error
            }
        }
    }

    //Для извлечения репозитория и подвязки его к приложению
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HotelApplication)
                val hotelRepository = application.container.hotelRepository
                HotelViewModel(hotelRepository = hotelRepository)
            }
        }
    }
}