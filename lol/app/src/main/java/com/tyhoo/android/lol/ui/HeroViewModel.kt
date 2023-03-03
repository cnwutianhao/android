package com.tyhoo.android.lol.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.lol.Result
import com.tyhoo.android.lol.domain.HeroInfo
import com.tyhoo.android.lol.domain.HeroSkin
import com.tyhoo.android.lol.domain.HeroSpell
import com.tyhoo.android.lol.domain.HeroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val useCase: HeroUseCase
) : ViewModel() {

    private val _heroInfo = mutableStateOf<HeroInfo?>(null)
    val heroInfo: State<HeroInfo?> = _heroInfo

    private val _heroSkins = mutableStateOf(emptyList<HeroSkin>())
    val heroSkins: State<List<HeroSkin>> = _heroSkins

    private val _heroSpells = mutableStateOf(emptyList<HeroSpell>())
    val heroSpells: State<List<HeroSpell>> = _heroSpells

    private val _version = mutableStateOf<String?>(null)
    val version: State<String?> = _version

    private val _fileTime = mutableStateOf<String?>(null)
    val fileTime: State<String?> = _fileTime

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadHero(heroId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = useCase.invoke(heroId)) {
                    is Result.Success -> {
                        _heroInfo.value = result.data.heroInfo
                        _heroSkins.value = result.data.skins
                        _heroSpells.value = result.data.spells
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