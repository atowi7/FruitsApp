package com.example.fruitsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitsapp.model.User
import com.example.fruitsapp.data.user.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun signup(username: String, pass: String, onSuccess: (Boolean) -> Unit) {

        viewModelScope.launch {
            val user = User(username = username, pass = pass)
            try {
                userRepository.insertUser(user)
                onSuccess(true)
            } catch (e: Exception) {
                onSuccess(false)
            }

        }
    }

    fun login(username: String, pass: String, onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUser(username, pass)

            if (user != null && user.pass == pass) {
                onSuccess(true)
            } else {
                onSuccess(false)
            }
        }
    }
}
