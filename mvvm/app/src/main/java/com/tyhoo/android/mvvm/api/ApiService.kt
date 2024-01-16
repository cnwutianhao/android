package com.tyhoo.android.mvvm.api

import com.tyhoo.android.mvvm.data.HeroResponse
import com.tyhoo.android.mvvm.data.ItemResponse
import com.tyhoo.android.mvvm.data.RuneResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("web201605/js/herolist.json")
    suspend fun heroList(): List<HeroResponse>

    @GET("web201605/js/item.json")
    suspend fun itemList(): List<ItemResponse>

    @GET("web201605/js/ming.json")
    suspend fun runeList(): List<RuneResponse>

    companion object {
        private const val BASE_URL = "https://pvp.qq.com/"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}