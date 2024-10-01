package com.example.fruitsapp.data.product

import com.example.fruitsapp.model.Fruit

class ProductRepository(private val apiService: ApiService) {
    suspend fun getProducts(): List<Fruit> {
        return apiService.getProducts()
    }
}