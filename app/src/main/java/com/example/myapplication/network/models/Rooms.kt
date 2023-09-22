package com.example.myapplication

import com.google.gson.annotations.SerializedName


data class Rooms (

  @SerializedName("rooms" ) var rooms : ArrayList<RoomModel> = arrayListOf()

)