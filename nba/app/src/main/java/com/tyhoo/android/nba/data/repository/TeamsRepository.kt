package com.tyhoo.android.nba.data.repository

import com.tyhoo.android.nba.data.db.TeamDao
import com.tyhoo.android.nba.data.db.TeamEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamsRepository @Inject constructor(private val teamDao: TeamDao) {

    fun getTeams(): Flow<List<TeamEntity>> {
        return teamDao.getTeams()
    }
}