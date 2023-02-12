package com.tyhoo.android.nba.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.tyhoo.android.nba.R
import com.tyhoo.android.nba.base.TAG
import com.tyhoo.android.nba.data.repository.SplashRepository
import com.tyhoo.android.nba.ui.splash.SplashFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: SplashRepository
) : ViewModel() {

    fun requestData(context: Context, owner: LifecycleOwner, view: View?) {
        viewModelScope.launch {
            val results = listOf(
                async { playersObserver(context, owner) },
                async { teamsObserver(context, owner) }
            )
            results.awaitAll()

            _loadingContent.value = context.getString(R.string.splash_load_finish)
            delay(1000)

            SplashFragmentDirections.actionSplashFragmentToHomeFragment().let { direction ->
                view?.findNavController()?.navigate(direction)
            }
        }
    }

    private suspend fun playersObserver(
        context: Context, owner: LifecycleOwner
    ) = Observer<Boolean> { result ->
        if (result == true) {
            Log.d(TAG, "球员数据请求成功")
        } else {
            Log.d(TAG, "球员数据请求失败")
        }
    }.apply {
        _loadingContent.value = context.getString(R.string.splash_load_players)
        players(context).observe(owner, this)
    }

    private suspend fun teamsObserver(
        context: Context, owner: LifecycleOwner
    ) = Observer<Boolean> { result ->
        if (result == true) {
            Log.d(TAG, "球队数据请求成功")
        } else {
            Log.d(TAG, "球队数据请求失败")
        }
    }.apply {
        _loadingContent.value = context.getString(R.string.splash_load_teams)
        teams(context).observe(owner, this)
    }

    private suspend fun players(context: Context): LiveData<Boolean> {
        return repository.players(context)
    }

    private suspend fun teams(context: Context): LiveData<Boolean> {
        return repository.teams(context)
    }

    private val _loadingContent = MutableLiveData<String>()
    val loadingContent: LiveData<String>
        get() = _loadingContent
}