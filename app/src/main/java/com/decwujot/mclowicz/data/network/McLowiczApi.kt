package com.decwujot.mclowicz.data.network

import com.decwujot.mclowicz.data.model.Dish
import retrofit2.http.GET

interface McLowiczApi {

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("food/random_food?size=20")
    suspend fun getDishes(): List<Dish>
}