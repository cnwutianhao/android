package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.mvvm.adapter.HeroDetailCoverAdapter
import com.tyhoo.android.mvvm.adapter.HeroDetailPicAdapter
import com.tyhoo.android.mvvm.adapter.HeroDetailSkillAdapter
import com.tyhoo.android.mvvm.data.HeroDetailRepository
import com.tyhoo.android.mvvm.data.HeroDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val repository: HeroDetailRepository
) : ViewModel() {

    fun requestData(
        owner: LifecycleOwner,
        heroIdName: String,
        coverAdapter: HeroDetailCoverAdapter,
        skillAdapter: HeroDetailSkillAdapter,
        picAdapter: HeroDetailPicAdapter
    ) = viewModelScope.launch {
        heroDetailObserver(owner, heroIdName, coverAdapter, skillAdapter, picAdapter)
    }

    private fun heroDetailObserver(
        owner: LifecycleOwner,
        heroIdName: String,
        coverAdapter: HeroDetailCoverAdapter,
        skillAdapter: HeroDetailSkillAdapter,
        picAdapter: HeroDetailPicAdapter
    ) = Observer<HeroDetailResponse> { heroDetail ->
        _heroName.value = heroDetail.heroName
        coverAdapter.submitList(heroDetail.coverList)
        skillAdapter.submitList(heroDetail.skillList)
        picAdapter.submitList(heroDetail.picList)
    }.apply {
        viewModelScope.launch {
            getHeroDetail(heroIdName).observe(owner, this@apply)
        }
    }

    private suspend fun getHeroDetail(heroIdName: String): LiveData<HeroDetailResponse> {
        return repository.getHeroDetail(heroIdName)
    }

    private val _heroName = MutableLiveData<String>()
    val heroName: LiveData<String>
        get() = _heroName
}