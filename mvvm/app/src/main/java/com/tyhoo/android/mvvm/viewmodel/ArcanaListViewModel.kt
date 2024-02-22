package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.mvvm.adapter.ArcanaAdapter
import com.tyhoo.android.mvvm.base.ARCANA_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.ARCANA_LIST_OFFSET
import com.tyhoo.android.mvvm.data.ArcanaRepository
import com.tyhoo.android.mvvm.data.ArcanaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArcanaListViewModel @Inject constructor(
    private val repository: ArcanaRepository
) : ViewModel() {

    fun requestData(
        owner: LifecycleOwner, rcv: RecyclerView, adapter: ArcanaAdapter
    ) = viewModelScope.launch {
        arcanaListObserver(owner, rcv, adapter)
    }

    private suspend fun arcanaListObserver(
        owner: LifecycleOwner,
        rcv: RecyclerView,
        adapter: ArcanaAdapter
    ) = Observer<List<ArcanaResponse>> { arcanaList ->
        adapter.submitList(arcanaList)

        val layoutManager = rcv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(
            ARCANA_LIST_FIRST_VISIBLE_ITEM_POSITION,
            ARCANA_LIST_OFFSET
        )
    }.apply {
        repository.getArcanaList().observe(owner, this)
    }
}