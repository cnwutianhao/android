package com.tyhoo.android.nba.data.response

import com.google.gson.annotations.SerializedName

data class PlayersResponse(
    @field:SerializedName("payload") val payload: PlayersPayload
)

data class PlayersPayload(
    @field:SerializedName("season") val season: PlayersSeason,
    @field:SerializedName("players") val players: List<PlayersPlayer>
)

data class PlayersSeason(
    @field:SerializedName("yearDisplay") val yearDisplay: String
)

data class PlayersPlayer(
    @field:SerializedName("playerProfile") val playerProfile: PlayersPlayerProfile,
    @field:SerializedName("teamProfile") val teamProfile: PlayersTeamProfile
)

/**
 * code -> lebron_james
 * country -> 美国
 * displayName -> 勒布朗 詹姆斯
 * height -> 2.06
 * jerseyNo -> 6
 * playerId -> 2544
 * position -> 前锋
 * weight -> 113.4 公斤
 */
data class PlayersPlayerProfile(
    @field:SerializedName("code") val code: String,
    @field:SerializedName("country") val country: String,
    @field:SerializedName("displayName") val displayName: String,
    @field:SerializedName("height") val height: String,
    @field:SerializedName("jerseyNo") val jerseyNo: String,
    @field:SerializedName("playerId") val playerId: String,
    @field:SerializedName("position") val position: String,
    @field:SerializedName("weight") val weight: String
)

/**
 * city -> 洛杉矶
 * name -> 湖人
 */
data class PlayersTeamProfile(
    @field:SerializedName("city") val city: String,
    @field:SerializedName("code") val code: String,
    @field:SerializedName("name") val name: String
)
