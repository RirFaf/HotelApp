package com.example.myapplication.data

import com.example.myapplication.SelectedRoomModel
import com.example.myapplication.network.BookingService

interface BookingRepository {
    suspend fun getSelectedRoom(): SelectedRoomModel
}

class NetworkBookingRepository(
    private val bookingService: BookingService
) : BookingRepository {
    override suspend fun getSelectedRoom(): SelectedRoomModel =
        bookingService.getSelectedRoom()
}
