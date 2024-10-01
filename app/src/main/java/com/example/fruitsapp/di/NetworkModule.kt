package com.example.fruitsapp.di

import com.example.fruitsapp.data.product.ApiService
import com.example.fruitsapp.data.product.ProductRepository
import com.example.fruitsapp.viewmodel.FruitViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//Model gor retrofit
val networkModule = module {
    single {
        Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/atowi7/mockjson/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}

//Model for repository and viewmodel
val productModule = module {
    single { ProductRepository(get()) }
    viewModel() { FruitViewModel(get()) }
}