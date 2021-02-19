package com.jackyli.androidarchitecture.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jackyli.androidarchitecture.model.entities.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * author : lijie
 * date : 2019/12/5 13:44
 * e-mail : jackyli706@gmail.com
 * description :
 */
@Dao
public interface UserDao {

    @Insert
    void insertOne(User user);

    @Query("SELECT * FROM User WHERE userName = 'admin'")
    List<User> getAdmin();

    @Query("SELECT * FROM User WHERE userName = (:name)")
    List<User> getUser(String name);

    @Query("SELECT * FROM User WHERE userName = (:name)")
    Maybe<List<User>> getUser2(String name);

    @Update
    Maybe<Integer> updateOneUser(User user);   //更新一条任务数据

    @Insert
    Completable insertOneUser(User user);    //添加一条新的任务

    @Query("Delete FROM User WHERE userName = (:name)")
    Single<Integer> deleteOneUser(String name);   //删除一条数据

    @Query("SELECT * FROM User")
    Maybe<List<User>> getAllUser();
}
