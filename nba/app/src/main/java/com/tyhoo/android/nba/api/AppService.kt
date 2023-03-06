package com.tyhoo.android.nba.api

import com.tyhoo.android.nba.base.BASE_URL
import com.tyhoo.android.nba.base.PLAYERS_URL
import com.tyhoo.android.nba.base.TEAMS_URL
import com.tyhoo.android.nba.data.response.PlayerResponse
import com.tyhoo.android.nba.data.response.PlayersResponse
import com.tyhoo.android.nba.data.response.TeamsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    /**
     * 球员列表
     * 请求地址：https://china.nba.cn/stats2/league/playerlist.json
     */
    @GET(PLAYERS_URL)
    suspend fun players(): PlayersResponse

    /**
     * 球队列表
     * 请求地址：https://china.nba.cn/stats2/league/conferenceteamlist.json
     */
    @GET(TEAMS_URL)
    suspend fun teams(): TeamsResponse

    /**
     * 球员详情
     * https://china.nba.cn/stats2/player/stats.json?playerCode=lebron_james
     */
    @GET("stats2/player/stats.json")
    suspend fun player(@Query("playerCode") playerCode: String): PlayerResponse

    companion object {
        fun create(): AppService {
            val logger = HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppService::class.java)
        }
    }
}