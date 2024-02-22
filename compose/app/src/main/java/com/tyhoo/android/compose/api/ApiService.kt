package com.tyhoo.android.compose.api

import com.tyhoo.android.compose.data.HeroResponse
import com.tyhoo.android.compose.data.EquipmentResponse
import com.tyhoo.android.compose.data.ArcanaResponse
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
    suspend fun equipmentList(): List<EquipmentResponse>

    @GET("web201605/js/ming.json")
    suspend fun arcanaList(): List<ArcanaResponse>

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