package com.jackyli.androidarchitecture.repository.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jackyli.androidarchitecture.model.UserInfo;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public abstract class UserDao {

    @Query("SELECT * FROM UserInfo")
    public abstract LiveData<List<UserInfo>> getUsers();

    @Insert
    public abstract Maybe<Integer> addUser(UserInfo userInfo);
}
