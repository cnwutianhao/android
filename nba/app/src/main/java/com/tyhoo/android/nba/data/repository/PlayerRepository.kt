package com.tyhoo.android.nba.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tyhoo.android.nba.api.AppService
import com.tyhoo.android.nba.data.response.PlayerResponse
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val service: AppService) {

    suspend fun player(code: String): LiveData<PlayerResponse> {
        val playerResponse = MutableLiveData<PlayerResponse>()
        try {
            val response = service.player(code)
            playerResponse.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return playerResponse
    }
}