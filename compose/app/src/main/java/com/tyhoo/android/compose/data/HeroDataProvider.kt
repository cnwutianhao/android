package com.tyhoo.android.compose.data

import com.tyhoo.android.compose.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.regex.Pattern
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

            // 英雄背景图（横版）
            val heroImgUrl = heroImgUrl(doc)
            // 英雄名
            val heroName = heroName(doc)
            // Cover
            val heroCoverList = heroCoverList(doc)
            // 英雄Id
            val heroId = heroId(doc)
            // 技能
            val heroSkillList = heroSkillList(doc)
            // 英雄皮肤
            val heroPicList = heroPicList(doc, heroId)

            HeroDetailResponse(
                heroName, heroImgUrl, heroId, heroCoverList, heroSkillList, heroPicList
            )
        }

    private fun heroName(doc: Document): String {
        var heroName = ""
        val coverName = doc.selectFirst(".cover-name")
        coverName?.let { name ->
            heroName = name.text()
        }
        return heroName
    }

    private fun heroImgUrl(doc: Document): String {
        val divElement = doc.selectFirst("div.zk-con1.zk-con")
        val styleAttribute = divElement?.attr("style")
        val urlRegex = "background:url\\('([^']+)'\\)".toRegex()
        val matchResult = urlRegex.find(styleAttribute ?: "")
        val imageUrl = matchResult?.groupValues?.getOrNull(1)
        return "https:$imageUrl"
    }

    private fun heroCoverList(doc: Document): List<HeroDetailCoverResponse> {
        val heroCoverList = mutableListOf<HeroDetailCoverResponse>()
        val coverList = doc.select(".cover-list li")
        coverList.forEach { element ->
            val text = element.selectFirst(".cover-list-text")
            val bar = element.selectFirst(".cover-list-bar")
            if (text != null && bar != null) {
                val labelText = text.text()
                val barWidth = bar.selectFirst(".ibar")?.attr("style").toString()
                val pattern = "\\d+"
                val regex = Pattern.compile(pattern)
                val matcher = regex.matcher(barWidth)
                val number = if (matcher.find()) matcher.group() else ""
                heroCoverList.add(HeroDetailCoverResponse(labelText, number))
            }
        }
        return heroCoverList
    }

    private fun heroSkillList(doc: Document): List<HeroDetailSkillResponse> {
        val heroSkillList = mutableListOf<HeroDetailSkillResponse>()
        val divSkillNames = doc.select("div.skill-show div.show-list p.skill-name b")
        val divSkillDescriptions = doc.select("div.skill-show div.show-list p.skill-desc")
        val divSkillImages = mutableListOf<String>()
        val imageElements = doc.select("ul.skill-u1 li img")
        imageElements.map {
            divSkillImages.add("https:" + it.attr("src"))
        }
        divSkillNames.forEachIndexed { i, _ ->
            val name = divSkillNames[i].text()
            val description = divSkillDescriptions[i].text()
            if (name != "") {
                heroSkillList.add(HeroDetailSkillResponse(name, description, divSkillImages[i]))
            }
        }

        return heroSkillList
    }

    private fun heroId(doc: Document): String {
        val hiddenSpan = doc.select("span.hidden")
        return hiddenSpan.text()
    }

    private fun heroPicList(doc: Document, heroId: String): List<HeroDetailPicResponse> {
        val heroPicList = mutableListOf<HeroDetailPicResponse>()
        val ulElement = doc.select("ul.pic-pf-list")
        val dataImgName = ulElement.attr("data-imgname")
        val names = dataImgName.split("|").map { it.split("&")[0] }
        names.forEachIndexed { i, name ->
            val picUrl =
                "https://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/$heroId/$heroId-mobileskin-${i + 1}.jpg"
            heroPicList.add(HeroDetailPicResponse(name, picUrl))
        }
        return heroPicList
    }
}