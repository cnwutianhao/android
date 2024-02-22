package com.tyhoo.android.mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArcanaRepository @Inject constructor(private val provider: ArcanaListDataProvider) {

    suspend fun getArcanaList(): LiveData<List<ArcanaResponse>> {
        val arcanaList = MutableLiveData<List<ArcanaResponse>>()
        val list = provider.provideArcanaListData()
        arcanaList.postValue(list)
        return arcanaList
    }
}