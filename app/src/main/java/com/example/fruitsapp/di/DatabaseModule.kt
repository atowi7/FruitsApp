package com.example.fruitsapp.di

import com.example.fruitsapp.data.user.UserDatabase
import com.example.fruitsapp.data.user.UserRepository
import com.example.fruitsapp.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single { UserDatabase.getDatabase(get()) }
    single { get<UserDatabase>().userDao() }
    single { UserRepository(get()) }
    viewModel { UserViewModel(get()) }
}