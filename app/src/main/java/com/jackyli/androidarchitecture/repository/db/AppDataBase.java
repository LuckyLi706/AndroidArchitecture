package com.jackyli.androidarchitecture.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jackyli.MvvmApplication;
import com.jackyli.androidarchitecture.model.UserInfo;

@Database(entities = {UserInfo.class}, version = 3, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static AppDataBase instance;

    public static AppDataBase getInstance() {
        synchronized (AppDataBase.class) {
            if (instance == null) {
                instance = Room.databaseBuilder(MvvmApplication.getContext(), AppDataBase.class, "Test").allowMainThreadQueries().build();
            }
            return instance;
        }
    }
}
