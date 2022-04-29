package com.example.diaryapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiaryItemBdModal::class], version = 1, exportSchema = false)
abstract class AppDataBaseDiary : RoomDatabase() {

    abstract fun diaryListDao(): DiaryListDao

    companion object {

        private var INSTANCE: AppDataBaseDiary? = null
        private val LOCK = Any()
        private const val DB_NAME = "base_diary"

        fun getInstance(application: Application): AppDataBaseDiary {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db =
                    Room.databaseBuilder(application, AppDataBaseDiary::class.java, DB_NAME).build()
                INSTANCE = db
                return db
            }
        }
    }
}