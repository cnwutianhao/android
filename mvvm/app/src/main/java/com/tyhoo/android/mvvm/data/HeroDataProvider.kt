package com.tyhoo.android.mvvm.data

import android.util.Log
import com.tyhoo.android.mvvm.api.ApiService
import com.tyhoo.android.mvvm.base.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

interface HeroListDataProvider {
    suspend fun provideHeroListData(): List<HeroResponse>
}

class HeroListDataProviderImpl @Inject constructor(
    private val service: ApiService
) : HeroListDataProvider {
    override suspend fun provideHeroListData(): List<HeroResponse> {
        return service.heroList()
    }
}

interface HeroDetailDataProvider {
    suspend fun provideHeroDetailData(heroIdName: String): HeroDetailResponse
}

class HeroDetailDataProviderImpl @Inject constructor() : HeroDetailDataProvider {
    override suspend fun provideHeroDetailData(heroIdName: String): HeroDetailResponse =
        withContext(Dispatchers.IO) {
            val url = "https://pvp.qq.com/web201605/herodetail/${heroIdName}.shtml"
            val doc = Jsoup.connect(url).get()

            // 背景图
            val divElement = doc.selectFirst("div.zk-con1.zk-con")
            val styleAttribute = divElement?.attr("style")
            val urlRegex = "background:url\\('([^']+)'\\)".toRegex()
            val matchResult = urlRegex.find(styleAttribute ?: "")
            val imageUrl = matchResult?.groupValues?.getOrNull(1)
            val heroImgUrl = "https:$imageUrl"

            // 英雄名
            var heroName = ""
            val coverName = doc.selectFirst(".cover-name")
            coverName?.let { name ->
                heroName = name.text()
            }

            val coverList = doc.select(".cover-list li")
            coverList.forEach { element ->
                val text = element.selectFirst(".cover-list-text")
                val bar = element.selectFirst(".cover-list-bar")
                if (text != null && bar != null) {
                    val labelText = text.text()
                    val barWidth = bar.selectFirst(".ibar")?.attr("style").toString()
                    Log.d(TAG, "labelText: $labelText")
                    Log.d(TAG, "barWidth: $barWidth")
                }
            }

            HeroDetailResponse(heroName, heroImgUrl)
        }
}