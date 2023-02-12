package com.tyhoo.android.nba.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TeamEntity::class], version = 1, exportSchema = false)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object {
        @Volatile
        private var instance: TeamDatabase? = null

        fun getInstance(context: Context): TeamDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TeamDatabase {
            return Room.databaseBuilder(context, TeamDatabase::class.java, "teams").build()
        }
    }
}