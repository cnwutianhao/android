package com.tyhoo.android.lol

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroesViewModel : ViewModel() {

    private val _heroes = mutableStateOf(emptyList<Hero>())
    val heroes: State<List<Hero>> = _heroes

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _version = mutableStateOf<String?>(null)
    val version: State<String?> = _version

    private val _fileTime = mutableStateOf<String?>(null)
    val fileTime: State<String?> = _fileTime

    init {
        loadHeroes()
    }

    private fun loadHeroes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = NetworkManager.getHeroes()) {
                    is Result.Success -> {
                        _heroes.value = result.data.heroes
                        _version.value = result.data.version
                        _fileTime.value = result.data.fileTime
                    }
                    is Result.Error -> {
                        _error.value = result.exception.message
                    }
                }
            }
        }
    }
}