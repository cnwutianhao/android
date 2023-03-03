package com.tyhoo.android.lol.domain

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @field:SerializedName("hero") val heroInfo: HeroInfo,
    @field:SerializedName("skins") val skins: List<HeroSkin>,
    @field:SerializedName("spells") val spells: List<HeroSpell>,
    @field:SerializedName("version") val version: String,
    @field:SerializedName("fileTime") val fileTime: String
)

data class HeroInfo(
    @field:SerializedName("heroId") val heroId: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("alias") val alias: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("roles") val roles: List<String>,
    @field:SerializedName("shortBio") val shortBio: String
)

data class HeroSkin(
    @field:SerializedName("skinId") val skinId: String,
    @field:SerializedName("heroId") val heroId: String,
    @field:SerializedName("heroName") val heroName: String,
    @field:SerializedName("heroTitle") val heroTitle: String,
    @field:SerializedName("name") val name: String
)

data class HeroSpell(
    @field:SerializedName("heroId") val heroId: String,
    @field:SerializedName("spellKey") val spellKey: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("description") val description: String
)