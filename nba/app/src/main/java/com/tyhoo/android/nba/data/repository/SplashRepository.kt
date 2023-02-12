package com.tyhoo.android.nba.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tyhoo.android.nba.api.AppService
import com.tyhoo.android.nba.data.db.PlayerDatabase
import com.tyhoo.android.nba.data.db.PlayerEntity
import com.tyhoo.android.nba.data.db.TeamDatabase
import com.tyhoo.android.nba.data.db.TeamEntity
import javax.inject.Inject

class SplashRepository @Inject constructor(private val service: AppService) {

    suspend fun players(context: Context): LiveData<Boolean> {
        val playersResponse = MutableLiveData<Boolean>()
        return try {
            val players = mutableListOf<PlayerEntity>()

            val response = service.players()
            response.payload.players.mapIndexed { i, player ->
                players.add(
                    PlayerEntity(
                        i, player.playerProfile.code, player.playerProfile.country,
                        player.playerProfile.displayName, player.playerProfile.height,
                        player.playerProfile.jerseyNo, player.playerProfile.playerId,
                        player.playerProfile.position, player.playerProfile.weight,
                        player.teamProfile.city, player.teamProfile.name
                    )
                )
            }

            val database = PlayerDatabase.getInstance(context)
            database.playerDao().deletePlayers()
            database.playerDao().insertPlayers(players)

            playersResponse.postValue(true)
            playersResponse
        } catch (e: Exception) {
            playersResponse.postValue(false)
            playersResponse
        }
    }

    suspend fun teams(context: Context): LiveData<Boolean> {
        val teamsResponse = MutableLiveData<Boolean>()
        return try {
            val teams = mutableListOf<TeamEntity>()

            val response = service.teams()
            response.payload.group.mapIndexed { index, group ->
                group.teams.mapIndexed { i, team ->
                    val indexId = if (index == 0) {
                        0
                    } else {
                        15
                    }

                    teams.add(
                        TeamEntity(
                            indexId + i, team.profile.abbr, team.profile.city,
                            team.profile.code, team.profile.displayConference,
                            team.profile.division, team.profile.name
                        )
                    )
                }
            }

            val database = TeamDatabase.getInstance(context)
            database.teamDao().deleteTeams()
            database.teamDao().insertTeams(teams)

            teamsResponse.postValue(true)
            teamsResponse
        } catch (e: Exception) {
            teamsResponse.postValue(false)
            teamsResponse
        }
    }
}