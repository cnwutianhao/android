package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        heroIdName: String
    ) = viewModelScope.launch {
        heroDetailObserver(owner, heroIdName)
    }

    private fun heroDetailObserver(
        owner: LifecycleOwner,
        heroIdName: String
    ) = Observer<HeroDetailResponse> { heroDetail ->
        _heroName.value = heroDetail.heroName
        _heroImgUrl.value = heroDetail.heroImgUrl
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

    private val _heroImgUrl = MutableLiveData<String>()
    val heroImgUrl: LiveData<String>
        get() = _heroImgUrl
}