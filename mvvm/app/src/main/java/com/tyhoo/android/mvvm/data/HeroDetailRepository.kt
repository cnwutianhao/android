package com.tyhoo.android.mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroDetailRepository @Inject constructor(private val provider: HeroDetailDataProvider) {

    suspend fun getHeroDetail(heroIdName: String): LiveData<HeroDetailResponse> {
        val heroDetail = MutableLiveData<HeroDetailResponse>()
        val detail = provider.provideHeroDetailData(heroIdName)
        heroDetail.postValue(detail)
        return heroDetail
    }
}