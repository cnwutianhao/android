package com.tyhoo.android.mvvm.data

data class HeroDetailResponse(
    val heroName: String,
    val heroImgUrl: String,
    val heroId: String,
    val coverList: List<HeroDetailCoverResponse>,
    val skillList: List<HeroDetailSkillResponse>,
    val picList: List<HeroDetailPicResponse>
)

data class HeroDetailCoverResponse(
    val label: String,
    val bar: String
)

data class HeroDetailSkillResponse(
    val skillName: String,
    val skillDescription: String,
    val skillImgUrl: String
)

data class HeroDetailPicResponse(
    val picName: String,
    val picUrl: String
)