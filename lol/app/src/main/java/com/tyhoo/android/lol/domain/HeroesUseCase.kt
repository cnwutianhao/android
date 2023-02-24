package com.tyhoo.android.lol.domain

import com.tyhoo.android.lol.data.HeroesRepository
import com.tyhoo.android.lol.Result
import javax.inject.Inject

class HeroesUseCase @Inject constructor(private val repository: HeroesRepository) {

    suspend operator fun invoke(): Result<HeroesResponse> {
        return repository.getHeroes()
    }
}