package com.tyhoo.android.nba.data.response

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @field:SerializedName("payload") val payload: PlayerPayload
)

data class PlayerPayload(
    @field:SerializedName("player") val player: Player
)

data class Player(
    @field:SerializedName("playerProfile") val playerProfile: PlayerProfile,
    @field:SerializedName("teamProfile") val teamProfile: PlayerTeamProfile
)

data class PlayerProfile(
    @field:SerializedName("code") val code: String,
    @field:SerializedName("country") val country: String,
    @field:SerializedName("displayName") val displayName: String,
    @field:SerializedName("draftYear") val draftYear: String,
    @field:SerializedName("experience") val experience: String,
    @field:SerializedName("height") val height: String,
    @field:SerializedName("jerseyNo") val jerseyNo: String,
    @field:SerializedName("playerId") val playerId: String,
    @field:SerializedName("position") val position: String,
    @field:SerializedName("weight") val weight: String
)

data class PlayerTeamProfile(
    @field:SerializedName("abbr") val abbr: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("code") val code: String,
    @field:SerializedName("conference") val conference: String,
    @field:SerializedName("displayAbbr") val displayAbbr: String,
    @field:SerializedName("displayConference") val displayConference: String,
    @field:SerializedName("division") val division: String,
    @field:SerializedName("name") val name: String
)
