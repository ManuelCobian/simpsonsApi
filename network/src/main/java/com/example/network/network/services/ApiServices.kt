package com.example.network.network.services

import com.example.network.Constants
import com.example.network.network.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET(Constants.PATH_INDEX)
    suspend fun searchPeople(
        @Query("count") limit: Int = 20,
        @Query("character") character: String?
    ):List<ApiResponse>
}