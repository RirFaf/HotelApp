package com.example.myapplication.network

import com.example.myapplication.Rooms
import retrofit2.http.GET

interface RoomsService {
    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRoom(): Rooms
}