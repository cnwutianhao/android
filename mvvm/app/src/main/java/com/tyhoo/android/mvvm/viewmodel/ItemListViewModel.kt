package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.mvvm.adapter.ItemAdapter
import com.tyhoo.android.mvvm.base.ITEM_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.ITEM_LIST_OFFSET
import com.tyhoo.android.mvvm.data.ItemRepository
import com.tyhoo.android.mvvm.data.ItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    fun requestData(
        owner: LifecycleOwner, rcv: RecyclerView, adapter: ItemAdapter
    ) = viewModelScope.launch {
        itemListObserver(owner, rcv, adapter)
    }

    private suspend fun itemListObserver(
        owner: LifecycleOwner,
        rcv: RecyclerView,
        adapter: ItemAdapter
    ) = Observer<List<ItemResponse>> { heroList ->
        adapter.submitList(heroList)

        val layoutManager = rcv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(
            ITEM_LIST_FIRST_VISIBLE_ITEM_POSITION,
            ITEM_LIST_OFFSET
        )
    }.apply {
        repository.getItemList().observe(owner, this)
    }
}