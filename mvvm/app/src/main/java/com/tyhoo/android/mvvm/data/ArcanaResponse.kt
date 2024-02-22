package com.tyhoo.android.mvvm.data

import com.google.gson.annotations.SerializedName

data class ArcanaResponse(
    @field:SerializedName("ming_id") val mingId: String,
    @field:SerializedName("ming_type") val mingType: String,
    @field:SerializedName("ming_grade") val mingGrade: String,
    @field:SerializedName("ming_name") val mingName: String,
    @field:SerializedName("ming_des") val mingDes: String
)