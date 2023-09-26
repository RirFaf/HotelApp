package com.example.myapplication.network

import com.example.myapplication.SelectedRoomModel
import retrofit2.http.GET

interface BookingService {
    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getSelectedRoom(): SelectedRoomModel
}