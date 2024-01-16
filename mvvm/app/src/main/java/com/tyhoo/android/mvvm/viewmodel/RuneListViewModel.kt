package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.mvvm.adapter.RuneAdapter
import com.tyhoo.android.mvvm.base.RUNE_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.RUNE_LIST_OFFSET
import com.tyhoo.android.mvvm.data.RuneRepository
import com.tyhoo.android.mvvm.data.RuneResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RuneListViewModel @Inject constructor(
    private val repository: RuneRepository
) : ViewModel() {

    fun requestData(
        owner: LifecycleOwner, rcv: RecyclerView, adapter: RuneAdapter
    ) = viewModelScope.launch {
        runeListObserver(owner, rcv, adapter)
    }

    private suspend fun runeListObserver(
        owner: LifecycleOwner,
        rcv: RecyclerView,
        adapter: RuneAdapter
    ) = Observer<List<RuneResponse>> { runeList ->
        adapter.submitList(runeList)

        val layoutManager = rcv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(
            RUNE_LIST_FIRST_VISIBLE_ITEM_POSITION,
            RUNE_LIST_OFFSET
        )
    }.apply {
        repository.getRuneList().observe(owner, this)
    }
}