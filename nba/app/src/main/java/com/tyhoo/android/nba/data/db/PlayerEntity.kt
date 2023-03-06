package com.tyhoo.android.nba.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val playerCode: String,
    val playerCountry: String,
    val playerName: String,
    val playerHeight: String,
    val playerJerseyNo: String,
    val playerId: String,
    val playerPosition: String,
    val playerWeight: String,
    val teamCity: String,
    val teamCode: String,
    val teamName: String
)
