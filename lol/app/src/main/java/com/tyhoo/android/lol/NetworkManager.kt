package com.tyhoo.android.lol

import okhttp3.OkHttpClient
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object NetworkManager {

    private const val BASE_URL = "https://game.gtimg.cn/"

    private val httpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ApiService::class.java)

    suspend fun getHeroes(): Result<HeroesResponse> {
        return try {
            val response = api.getHeroes()
            Result.Success(response)
        } catch (e: IOException) {
            Result.Error(e)
        }
    }

    private interface ApiService {
        @GET("images/lol/act/img/js/heroList/hero_list.js?ts=2794914")
        suspend fun getHeroes(): HeroesResponse
    }
}