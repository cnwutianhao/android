package com.tyhoo.android.lol.data

import com.tyhoo.android.lol.domain.HeroesResponse
import com.tyhoo.android.lol.Result
import okio.IOException
import javax.inject.Inject

interface HeroesRepository {
    suspend fun getHeroes(): Result<HeroesResponse>
}

class HeroesRepositoryImpl @Inject constructor(
    private val service: ApiService
) : HeroesRepository {

    override suspend fun getHeroes(): Result<HeroesResponse> {
        return try {
            val response = service.getHeroes()
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}