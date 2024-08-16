package com.example.network.network.models

import com.google.gson.annotations.SerializedName

data class ApiModel(
    val id: Int,
    val quote: String,
    val character: String,
    val image: String,
    val descrip: String,
)
