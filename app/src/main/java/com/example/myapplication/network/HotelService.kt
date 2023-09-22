package com.example.myapplication.network

import com.example.myapplication.network.models.HotelModel
import retrofit2.http.GET

interface HotelService {
    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotel():HotelModel
}