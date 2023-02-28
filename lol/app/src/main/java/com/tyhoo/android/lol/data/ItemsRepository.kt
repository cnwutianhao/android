package com.tyhoo.android.lol.data

import com.tyhoo.android.lol.domain.ItemsResponse
import com.tyhoo.android.lol.Result
import okio.IOException
import javax.inject.Inject

interface ItemsRepository {
    suspend fun getItems(): Result<ItemsResponse>
}

class ItemsRepositoryImpl @Inject constructor(
    private val service: ApiService
) : ItemsRepository {

    override suspend fun getItems(): Result<ItemsResponse> {
        return try {
            val response = service.getItems()
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}