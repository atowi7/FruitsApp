package com.example.fruitsapp.data.product

import com.example.fruitsapp.model.Fruit
import retrofit2.http.GET

interface ApiService {
    @GET("fruits")
    suspend fun getProducts():List<Fruit>

}