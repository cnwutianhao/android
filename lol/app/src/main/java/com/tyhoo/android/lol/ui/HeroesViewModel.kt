package com.tyhoo.android.lol.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.lol.Result
import com.tyhoo.android.lol.domain.Hero
import com.tyhoo.android.lol.domain.HeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val useCase: HeroesUseCase
) : ViewModel() {

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
                when (val result = useCase.invoke()) {
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