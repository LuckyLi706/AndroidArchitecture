package com.jackyli.androidarchitecture.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jackyli.androidarchitecture.MvpApplication;
import com.jackyli.androidarchitecture.model.entities.User;

/**
 * author : lijie
 * date : 2019/12/4 10:53
 * e-mail : jackyli706@gmail.com
 * description :
 */
@Database(entities = {User.class}, version = 3, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static AppDataBase instance;

    public static AppDataBase getInstance() {
        synchronized (AppDataBase.class) {
            if (instance == null) {
                instance = Room.databaseBuilder(MvpApplication.getContext(), AppDataBase.class, "Test").allowMainThreadQueries().build();
            }
            return instance;
        }
    }
}
