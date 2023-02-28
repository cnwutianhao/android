package com.tyhoo.android.lol.domain

import com.tyhoo.android.lol.Result
import com.tyhoo.android.lol.data.ItemsRepository
import javax.inject.Inject

class ItemsUseCase @Inject constructor(private val repository: ItemsRepository) {

    suspend operator fun invoke(): Result<ItemsResponse> {
        return repository.getItems()
    }
}