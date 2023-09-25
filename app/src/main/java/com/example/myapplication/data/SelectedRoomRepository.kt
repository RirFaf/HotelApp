package com.example.myapplication.data

import com.example.myapplication.SelectedRoomModel
import com.example.myapplication.network.SelectedRoomService

interface SelectedRoomRepository {
    suspend fun getSelectedRoom(): SelectedRoomModel
}

class NetworkSelectedRoomRepository(
    private val selectedRoomService: SelectedRoomService
) : SelectedRoomRepository {
    override suspend fun getSelectedRoom(): SelectedRoomModel =
        selectedRoomService.getSelectedRoom()
}
