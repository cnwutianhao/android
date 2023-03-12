package com.tyhoo.android.nba.viewmodel

import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.nba.adapter.PlayersAdapter
import com.tyhoo.android.nba.data.db.PlayerEntity
import com.tyhoo.android.nba.data.repository.PlayersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val repository: PlayersRepository
) : ViewModel() {

    fun requestData(owner: LifecycleOwner, adapter: PlayersAdapter, playerList: RecyclerView) {
        playersObserver(owner, adapter, playerList)
    }

    private fun playersObserver(
        owner: LifecycleOwner,
        adapter: PlayersAdapter,
        playerList: RecyclerView
    ) = Observer<List<PlayerEntity>> { players ->
        adapter.submitList(players)
        scrollPosition?.let { prePosition ->
            playerList.scrollToPosition(prePosition)
        }
    }.apply {
        players.observe(owner, this)
    }

    private val players: LiveData<List<PlayerEntity>>
        get() = repository.getPlayers().asLiveData()

    private val _scrollPosition = MutableLiveData<Int>()
    private val scrollPosition: Int?
        get() = _scrollPosition.value

    fun setScrollPosition(position: Int) {
        _scrollPosition.value = position
    }
}