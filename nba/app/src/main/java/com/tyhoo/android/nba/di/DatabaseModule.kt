package com.tyhoo.android.nba.di

import android.content.Context
import com.tyhoo.android.nba.data.db.PlayerDao
import com.tyhoo.android.nba.data.db.PlayerDatabase
import com.tyhoo.android.nba.data.db.TeamDao
import com.tyhoo.android.nba.data.db.TeamDatabase
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

    @Singleton
    @Provides
    fun provideTeamDatabase(@ApplicationContext context: Context): TeamDatabase {
        return TeamDatabase.getInstance(context)
    }

    @Provides
    fun provideTeamDao(database: TeamDatabase): TeamDao {
        return database.teamDao()
    }
}