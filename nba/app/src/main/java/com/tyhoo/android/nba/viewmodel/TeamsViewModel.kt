package com.tyhoo.android.nba.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tyhoo.android.nba.adapter.TeamsAdapter
import com.tyhoo.android.nba.data.db.TeamEntity
import com.tyhoo.android.nba.data.repository.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
        private val repository: TeamsRepository
) : ViewModel() {

    fun requestData(owner: LifecycleOwner, adapter: TeamsAdapter) {
        teamsObserver(owner, adapter)
    }

    private fun teamsObserver(
            owner: LifecycleOwner,
            adapter: TeamsAdapter
    ) = Observer<List<TeamEntity>> { teams ->
        adapter.submitList(teams)
    }.apply {
        teams.observe(owner, this)
    }

    private val teams: LiveData<List<TeamEntity>>
        get() = repository.getTeams().asLiveData()
}