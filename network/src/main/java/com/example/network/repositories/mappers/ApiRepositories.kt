package com.example.network.repositories.mappers

import com.example.network.network.ApiResponseStatus
import com.example.network.network.makeNetworkCall
import com.example.network.network.models.ApiModel
import com.example.network.network.services.ApiServices
import javax.inject.Inject

class ApiRepositories @Inject constructor(private val service: ApiServices) {
    suspend fun getSearchPeople(limit: Int, search: String): ApiResponseStatus<List<ApiModel>> =
        makeNetworkCall {
            service.searchPeople(limit, search).map { it.toMapModel() }
        }
}