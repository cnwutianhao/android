package com.tyhoo.android.nba.data.response

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @field:SerializedName("payload") val payload: TeamsPayload
)

data class TeamsPayload(
    @field:SerializedName("season") val season: TeamsSeason,
    @field:SerializedName("listGroups") val group: List<TeamsGroup>
)

data class TeamsSeason(
    @field:SerializedName("yearDisplay") val yearDisplay: String
)

data class TeamsGroup(
    @field:SerializedName("teams") val teams: List<TeamsTeam>,
    @field:SerializedName("conference") val conference: String
)

data class TeamsTeam(
    @field:SerializedName("profile") val profile: TeamsTeamProfile
)

/**
 * abbr -> LAL
 * city -> 洛杉矶
 * code -> lakers
 * displayConference -> 西部
 * division -> 太平洋分区
 * name -> 湖人
 */
data class TeamsTeamProfile(
    @field:SerializedName("abbr") val abbr: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("code") val code: String,
    @field:SerializedName("displayConference") val displayConference: String,
    @field:SerializedName("division") val division: String,
    @field:SerializedName("name") val name: String
)