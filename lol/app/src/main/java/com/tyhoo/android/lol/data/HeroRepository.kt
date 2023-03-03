package com.tyhoo.android.lol.data

import com.tyhoo.android.lol.domain.HeroResponse
import com.tyhoo.android.lol.Result
import okio.IOException
import javax.inject.Inject

interface HeroRepository {
    suspend fun getHero(heroId: String): Result<HeroResponse>
}

class HeroRepositoryImpl @Inject constructor(
    private val service: ApiService
) : HeroRepository {

    override suspend fun getHero(heroId: String): Result<HeroResponse> {
        return try {
            val response = service.getHero(heroId)
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}