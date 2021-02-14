package com.development.loginmvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.development.loginmvvm.model.itunes.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun collectionDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDataseClient(context: Context) : AppDB {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDB::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}