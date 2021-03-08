package com.jackyli.androidarchitecture.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jackyli.androidarchitecture.model.UserInfo

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo")
    fun getUsers(): LiveData<List<UserInfo>>

    @Insert
    fun insertUser(userInfo: UserInfo)
}