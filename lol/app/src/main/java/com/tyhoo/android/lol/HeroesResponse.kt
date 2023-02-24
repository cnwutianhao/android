package com.tyhoo.android.lol

import com.google.gson.annotations.SerializedName

data class HeroesResponse(
    @field:SerializedName("hero") val heroes: List<Hero>,
    @field:SerializedName("version") val version: String,
    @field:SerializedName("fileTime") val fileTime: String
)

data class Hero(
    @field:SerializedName("heroId") val heroId: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("alias") val alias: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("roles") val roles: List<String>
)