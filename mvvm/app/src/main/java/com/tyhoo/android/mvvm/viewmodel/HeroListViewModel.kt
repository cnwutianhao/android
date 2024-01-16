package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.mvvm.adapter.HeroAdapter
import com.tyhoo.android.mvvm.base.HERO_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.HERO_LIST_OFFSET
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
        owner: LifecycleOwner, rcv: RecyclerView, adapter: HeroAdapter
    ) = viewModelScope.launch {
        heroListObserver(owner, rcv, adapter)
    }

    private suspend fun heroListObserver(
        owner: LifecycleOwner,
        rcv: RecyclerView,
        adapter: HeroAdapter
    ) = Observer<List<HeroResponse>> { heroList ->
        adapter.submitList(heroList)

        val layoutManager = rcv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(
            HERO_LIST_FIRST_VISIBLE_ITEM_POSITION,
            HERO_LIST_OFFSET
        )
    }.apply {
        repository.getHeroList().observe(owner, this)
    }
}