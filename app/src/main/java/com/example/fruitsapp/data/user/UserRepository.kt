package com.example.fruitsapp.data.user

import com.example.fruitsapp.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User){
         userDao.insertUser(user)
    }

    suspend fun getUser(username:String, pass:String): User?{
       return userDao.getUser(username,pass)
    }
}