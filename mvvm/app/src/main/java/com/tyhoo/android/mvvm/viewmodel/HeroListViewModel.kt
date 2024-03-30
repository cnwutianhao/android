package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.mvvm.base.HERO_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.HERO_LIST_OFFSET
import com.tyhoo.android.mvvm.data.HeroRepository
import com.tyhoo.android.mvvm.data.HeroResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    fun requestData(owner: LifecycleOwner) = viewModelScope.launch {
        if (heroList.value != null) {
            return@launch
        }
        heroListObserver(owner)
    }

    private suspend fun heroListObserver(
        owner: LifecycleOwner
    ) = Observer<List<HeroResponse>> { heroList ->
        fullHeroList = heroList
        _heroList.value = heroList
    }.apply {
        repository.getHeroList().observe(owner, this)
    }

    fun fabMenu() {
        _showFabMenu.value = _showFabMenu.value != true
    }

    fun heroSearch(heroName: String) {
        val searchList = mutableListOf<List<HeroResponse>>()
        fullHeroList.forEach { hero ->
            if (hero.cName.contains(heroName)) {
                searchList.add(listOf(hero))
            }
        }

        // 将 searchList 中的数据扁平化后赋值给 _heroList 的 value 属性
        _heroList.value = searchList.flatten()

        HERO_LIST_FIRST_VISIBLE_ITEM_POSITION = 0
        HERO_LIST_OFFSET = 0
    }

    fun heroRevert() {
        _heroList.value = emptyList()
        _heroList.value = fullHeroList

        HERO_LIST_FIRST_VISIBLE_ITEM_POSITION = 0
        HERO_LIST_OFFSET = 0
    }

    private val _heroList = MutableLiveData<List<HeroResponse>>()
    val heroList: LiveData<List<HeroResponse>>
        get() = _heroList

    private var fullHeroList = listOf<HeroResponse>()

    private val _showFabMenu = MutableLiveData<Boolean>()
    val showFabMenu: LiveData<Boolean>
        get() = _showFabMenu
}