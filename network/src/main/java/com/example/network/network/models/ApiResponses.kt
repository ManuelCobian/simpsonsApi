package com.example.network.network.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("quote") var quote: String,
    @SerializedName("character") var character: String,
    @SerializedName("image") var image: String,
    @SerializedName("characterDirection") var descrip: String,
)