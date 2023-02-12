package com.tyhoo.android.nba.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlayerEntity::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var instance: PlayerDatabase? = null

        fun getInstance(context: Context): PlayerDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PlayerDatabase {
            return Room.databaseBuilder(context, PlayerDatabase::class.java, "players").build()
        }
    }
}