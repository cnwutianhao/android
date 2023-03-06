package com.tyhoo.android.nba.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tyhoo.android.nba.data.NavToPlayer
import com.tyhoo.android.nba.data.repository.PlayerRepository
import com.tyhoo.android.nba.data.response.PlayerPayload
import com.tyhoo.android.nba.data.response.PlayerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {

    suspend fun requestData(owner: LifecycleOwner, playerData: NavToPlayer) {
        _teamCode.value = playerData.teamCode
        playerObserver(owner, playerData)
    }

    private suspend fun playerObserver(
        owner: LifecycleOwner, playerData: NavToPlayer
    ) = Observer<PlayerResponse> { player ->
        _payload.value = player.payload
    }.apply {
        player(playerData.playerCode).observe(owner, this)
    }

    private suspend fun player(code: String): LiveData<PlayerResponse> {
        return repository.player(code)
    }

    private val _teamCode = MutableLiveData<String>()
    val teamCode: LiveData<String>
        get() = _teamCode

    private val _payload = MutableLiveData<PlayerPayload>()
    val payload: LiveData<PlayerPayload>
        get() = _payload
}