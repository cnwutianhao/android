package com.tyhoo.android.compose.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroRepository @Inject constructor(private val provider: HeroListDataProvider) {

    suspend fun getHeroList(): LiveData<List<HeroResponse>> {
        val heroList = MutableLiveData<List<HeroResponse>>()
        val list = provider.provideHeroListData()
        heroList.postValue(list)
        return heroList
    }
}