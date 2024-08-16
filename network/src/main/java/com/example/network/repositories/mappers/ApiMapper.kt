package com.example.network.repositories.mappers

import com.example.network.network.models.ApiModel
import com.example.network.network.models.ApiResponse

internal fun ApiResponse.toMapModel(): ApiModel =
    ApiModel(
        quote = this.quote,
        character = this.character,
        image = this.image,
        descrip = this.descrip
    )