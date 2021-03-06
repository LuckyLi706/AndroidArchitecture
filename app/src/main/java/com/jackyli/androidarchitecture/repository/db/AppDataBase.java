package com.jackyli.androidarchitecture.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jackyli.MvvmApplication;
import com.jackyli.androidarchitecture.model.UserInfo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {UserInfo.class}, version = 3, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static final Executor executor = Executors.newCachedThreadPool();

    private static AppDataBase instance;

    public static AppDataBase getInstance() {
        synchronized (AppDataBase.class) {
            if (instance == null) {
                instance = Room.databaseBuilder(MvvmApplication.getContext(), AppDataBase.class, "Test").setQueryExecutor(executor).build();
            }
            return instance;
        }
    }
}
