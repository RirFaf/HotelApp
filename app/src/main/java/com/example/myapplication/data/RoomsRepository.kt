package com.example.myapplication.data

import com.example.myapplication.Rooms
import com.example.myapplication.network.RoomsService

interface RoomsRepository {
    suspend fun getRooms(): Rooms
}

class NetworkRoomsRepository(
    private val roomsService: RoomsService
) : RoomsRepository{
    override suspend fun getRooms(): Rooms = roomsService.getRoom()
}