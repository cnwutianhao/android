package com.tyhoo.android.nba.di

import android.content.Context
import com.tyhoo.android.nba.data.db.PlayerDao
import com.tyhoo.android.nba.data.db.PlayerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providePlayerDatabase(@ApplicationContext context: Context): PlayerDatabase {
        return PlayerDatabase.getInstance(context)
    }

    @Provides
    fun providePlayerDao(database: PlayerDatabase): PlayerDao {
        return database.playerDao()
    }
}