package com.tyhoo.android.mvvm.data

import com.google.gson.annotations.SerializedName

data class EquipmentResponse(
    @field:SerializedName("item_id") val itemId: Int,
    @field:SerializedName("item_name") val itemName: String,
    @field:SerializedName("item_type") val itemType: Int,
    @field:SerializedName("price") val price: Int,
    @field:SerializedName("total_price") val totalPrice: Int,
    @field:SerializedName("des1") val des1: String,
    @field:SerializedName("des2") val des2: String?
)