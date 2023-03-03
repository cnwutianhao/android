package com.tyhoo.android.lol.domain

import com.tyhoo.android.lol.data.HeroRepository
import com.tyhoo.android.lol.Result
import javax.inject.Inject

class HeroUseCase @Inject constructor(private val repository: HeroRepository) {

    suspend operator fun invoke(heroId: String): Result<HeroResponse> {
        return repository.getHero(heroId)
    }
}