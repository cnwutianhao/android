package com.tyhoo.android.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.data.HeroRepository
import com.tyhoo.android.compose.data.HeroResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject internal constructor(
    private val repository: HeroRepository
) : ViewModel() {

    init {
        fetchHeroList()
    }

    private fun fetchHeroList() {
        viewModelScope.launch {
            val result = repository.getHeroList()
            result.observeForever { heroList ->
                _heroList.value = heroList
            }
        }
    }

    private val _heroList = MutableLiveData<List<HeroResponse>>()
    val heroList: LiveData<List<HeroResponse>>
        get() = _heroList
}