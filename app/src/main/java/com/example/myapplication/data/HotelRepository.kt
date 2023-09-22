package com.example.myapplication.data

import com.example.myapplication.network.HotelService
import com.example.myapplication.network.models.HotelModel


//Инструмент для общения с API
interface HotelRepository {
    suspend fun getHotel(): HotelModel
}

class NetworkHotelRepository(
    private val hotelService: HotelService
) : HotelRepository {
    override suspend fun getHotel(): HotelModel = hotelService.getHotel()
}