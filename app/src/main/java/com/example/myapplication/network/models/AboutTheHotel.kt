package com.example.myapplication.network.models

import com.google.gson.annotations.SerializedName


data class AboutTheHotel (

  @SerializedName("description"   ) var description   : String?           = null,
  @SerializedName("peculiarities" ) var peculiarities : ArrayList<String> = arrayListOf()

)