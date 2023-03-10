package com.tyhoo.android.lol.data

import com.tyhoo.android.lol.domain.HeroResponse
import com.tyhoo.android.lol.domain.HeroesResponse
import com.tyhoo.android.lol.domain.ItemsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    /**
     * 英雄列表
     * 请求地址：https://game.gtimg.cn/images/lol/act/img/js/heroList/hero_list.js?ts=2794914
     */
    @GET("images/lol/act/img/js/heroList/hero_list.js?ts=2794914")
    suspend fun getHeroes(): HeroesResponse

    /**
     * 装备列表
     * 请求地址：https://game.gtimg.cn/images/lol/act/img/js/items/items.js?ts=2794915
     */
    @GET("images/lol/act/img/js/items/items.js?ts=2794915")
    suspend fun getItems(): ItemsResponse

    /**
     * 英雄详情
     * 请求地址：https://game.gtimg.cn/images/lol/act/img/js/hero/1.js?ts=2794916
     */
    @GET("images/lol/act/img/js/hero/{heroId}.js?ts=2794916")
    suspend fun getHero(@Path("heroId") heroId: String): HeroResponse

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