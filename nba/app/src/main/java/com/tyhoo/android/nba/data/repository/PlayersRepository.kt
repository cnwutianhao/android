package com.tyhoo.android.nba.data.repository

import com.tyhoo.android.nba.data.db.PlayerDao
import com.tyhoo.android.nba.data.db.PlayerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayersRepository @Inject constructor(private val playerDao: PlayerDao) {

    fun getPlayers(): Flow<List<PlayerEntity>> {
        return playerDao.getPlayers()
    }
}