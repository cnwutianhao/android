package com.tyhoo.android.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.data.HeroDetailRepository
import com.tyhoo.android.compose.data.HeroDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: HeroDetailRepository
) : ViewModel() {

    init {
        val heroIdName = savedStateHandle.get<String>("heroIdName")
        fetchHeroDetail(heroIdName)
    }

    private fun fetchHeroDetail(heroIdName: String?) {
        heroIdName?.let { idName ->
            viewModelScope.launch {
                val result = repository.getHeroDetail(idName)
                result.observeForever { heroDetail ->
                    _heroDetail.value = heroDetail
                }
            }
        }
    }

    private val _heroDetail = MutableLiveData<HeroDetailResponse>()
    val heroDetail: LiveData<HeroDetailResponse>
        get() = _heroDetail
}