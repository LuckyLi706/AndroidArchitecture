package com.jackyli.androidarchitecture.repository.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jackyli.androidarchitecture.MvvmApplication
import com.jackyli.androidarchitecture.model.UserInfo

@Database(entities = [UserInfo::class], version = 1, exportSchema = false)
public abstract class AppDataBase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase().also { instance = it }
            }
        }

        private fun buildDatabase(): AppDataBase {
            return Room.databaseBuilder(MvvmApplication.getContext(), AppDataBase::class.java, "Test").build()
        }
    }

    public abstract fun getUserDao(): UserDao
}