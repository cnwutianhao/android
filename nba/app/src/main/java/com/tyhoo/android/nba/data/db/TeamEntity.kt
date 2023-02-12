package com.tyhoo.android.nba.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val abbr: String,
    val city: String,
    val code: String,
    val displayConference: String,
    val division: String,
    val name: String
)
