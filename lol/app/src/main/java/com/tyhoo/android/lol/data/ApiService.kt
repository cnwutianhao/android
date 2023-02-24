package com.tyhoo.android.lol.data

import com.tyhoo.android.lol.domain.HeroesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    /**
     * 英雄列表
     * 请求地址：https://game.gtimg.cn/images/lol/act/img/js/heroList/hero_list.js?ts=2794914
     */
    @GET("images/lol/act/img/js/heroList/hero_list.js?ts=2794914")
    suspend fun getHeroes(): HeroesResponse

    companion object {
        fun create(): ApiService {
            val logger = HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://game.gtimg.cn/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}