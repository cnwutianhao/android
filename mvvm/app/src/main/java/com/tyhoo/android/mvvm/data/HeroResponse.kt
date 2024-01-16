package com.tyhoo.android.mvvm.data

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @field:SerializedName("ename") val eName: Int,
    @field:SerializedName("cname") val cName: String,
    @field:SerializedName("id_name") val idName: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("pay_type") val payType: Int?,
    @field:SerializedName("new_type") val newType: Int,
    @field:SerializedName("hero_type") val heroType: Int,
    @field:SerializedName("hero_type2") val heroType2: Int?,
    @field:SerializedName("skin_name") val skinName: String,
    @field:SerializedName("moss_id") val mossId: Int
)