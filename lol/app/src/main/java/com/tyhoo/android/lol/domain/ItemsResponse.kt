package com.tyhoo.android.lol.domain

import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @field:SerializedName("items") val items: List<Item>,
    @field:SerializedName("version") val version: String,
    @field:SerializedName("fileTime") val fileTime: String
)

data class Item(
    @field:SerializedName("itemId") val itemId: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("iconPath") val iconPath: String,
    @field:SerializedName("price") val price: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("maps") val maps: List<String>,
    @field:SerializedName("plaintext") val plaintext: String,
    @field:SerializedName("sell") val sell: String,
    @field:SerializedName("total") val total: String
)