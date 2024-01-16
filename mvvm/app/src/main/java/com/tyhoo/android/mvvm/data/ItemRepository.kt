package com.tyhoo.android.mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(private val provider: ItemListDataProvider) {

    suspend fun getItemList(): LiveData<List<ItemResponse>> {
        val itemList = MutableLiveData<List<ItemResponse>>()
        val list = provider.provideItemListData()
        itemList.postValue(list)
        return itemList
    }
}