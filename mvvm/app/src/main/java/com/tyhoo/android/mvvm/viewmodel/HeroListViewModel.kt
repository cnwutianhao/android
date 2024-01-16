package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.mvvm.adapter.HeroAdapter
import com.tyhoo.android.mvvm.data.HeroRepository
import com.tyhoo.android.mvvm.data.HeroResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    fun requestData(
        owner: LifecycleOwner, adapter: HeroAdapter
    ) = viewModelScope.launch {
        heroListObserver(owner, adapter)
    }

    private suspend fun heroListObserver(
        owner: LifecycleOwner,
        adapter: HeroAdapter
    ) = Observer<List<HeroResponse>> { heroList ->
        adapter.submitList(heroList)
    }.apply {
        repository.getHeroList().observe(owner, this)
    }
}