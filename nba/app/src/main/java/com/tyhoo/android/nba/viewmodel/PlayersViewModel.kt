package com.tyhoo.android.nba.viewmodel

import androidx.lifecycle.*
import com.tyhoo.android.nba.adapter.PlayersAdapter
import com.tyhoo.android.nba.data.db.PlayerEntity
import com.tyhoo.android.nba.data.repository.PlayersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val repository: PlayersRepository
) : ViewModel() {

    fun requestData(owner: LifecycleOwner, adapter: PlayersAdapter) {
        playersObserver(owner, adapter)
    }

    private fun playersObserver(
        owner: LifecycleOwner,
        adapter: PlayersAdapter
    ) = Observer<List<PlayerEntity>> { players ->
        adapter.submitList(players)
    }.apply {
        players.observe(owner, this)
    }

    private val players: LiveData<List<PlayerEntity>>
        get() = repository.getPlayers().asLiveData()
}