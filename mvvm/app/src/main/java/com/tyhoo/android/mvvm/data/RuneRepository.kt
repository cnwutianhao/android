package com.tyhoo.android.mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RuneRepository @Inject constructor(private val provider: RuneListDataProvider) {

    suspend fun getRuneList(): LiveData<List<RuneResponse>> {
        val runeList = MutableLiveData<List<RuneResponse>>()
        val list = provider.provideRuneListData()
        runeList.postValue(list)
        return runeList
    }
}