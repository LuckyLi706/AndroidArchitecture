package com.jackyli.androidarchitecture.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
        @PrimaryKey
        var id: Int,
        var userName: String,
        var passWord: String
)
