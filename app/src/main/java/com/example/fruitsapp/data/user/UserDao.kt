package com.example.fruitsapp.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fruitsapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND pass = :pass LIMIT 1")
    suspend fun getUser(username:String,pass:String): User?

}