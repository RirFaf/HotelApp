package com.example.myapplication.data

import com.example.myapplication.network.HotelService
import com.example.myapplication.network.RoomsService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

interface AppContainer {
    val hotelRepository: HotelRepository
    val roomsRepository: RoomsRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL = "https://run.mocky.io/v3/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    //Для вызова к API
    private val retrofitHotelService: HotelService by lazy {
        retrofit.create(HotelService::class.java)
    }
    private val retrofitRoomsService: RoomsService by lazy {
        retrofit.create(RoomsService::class.java)
    }

    //Гарантирует, что репозиторий будет создан только единожды
    override val hotelRepository: HotelRepository by lazy {
        NetworkHotelRepository(retrofitHotelService)
    }

    override val roomsRepository: RoomsRepository by lazy {
        NetworkRoomsRepository(retrofitRoomsService)
    }
}