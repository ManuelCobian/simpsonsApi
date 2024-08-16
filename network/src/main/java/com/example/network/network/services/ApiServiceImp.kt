package com.example.network.network.services

import com.example.network.network.models.ApiResponse
import retrofit2.Retrofit
import javax.inject.Inject


class ApiServiceImp @Inject constructor(private val retrofit: Retrofit) : ApiServices {
    override suspend fun searchPeople(limit: Int, character: String?): List<ApiResponse> {
        val service = retrofit.create(ApiServices::class.java)
        return service.searchPeople(limit,character)
    }
}