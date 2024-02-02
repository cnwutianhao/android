package com.tyhoo.android.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tyhoo.android.compose.data.HeroDetailCoverResponse
import com.tyhoo.android.compose.data.HeroDetailPicResponse
import com.tyhoo.android.compose.data.HeroDetailResponse
import com.tyhoo.android.compose.data.HeroDetailSkillResponse
import com.tyhoo.android.compose.viewmodel.HeroDetailViewModel

@Composable
fun HeroDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: HeroDetailViewModel = hiltViewModel()
) {
    val heroDetail by viewModel.heroDetail.observeAsState(
        initial = HeroDetailResponse(
            "", "", "",
            emptyList(),
            emptyList(),
            emptyList()
        )
    )
    HeroDetailScreen(heroDetail, modifier)
}

@Composable
fun HeroDetailScreen(
    heroDetail: HeroDetailResponse,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = heroDetail.heroName,
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
        }

        itemsIndexed(items = heroDetail.coverList) { _, cover ->
            HeroDetailCoverItem(item = cover)
        }

        itemsIndexed(items = heroDetail.skillList) { _, skill ->
            HeroDetailSkillItem(item = skill)
        }

        item {
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 16.dp)
            ) {
                items(items = heroDetail.picList) { pic ->
                    HeroDetailPicItem(item = pic)
                }
            }
        }
    }
}

@Composable
fun HeroDetailCoverItem(item: HeroDetailCoverResponse) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = item.label + "：",
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = item.bar,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun HeroDetailSkillItem(item: HeroDetailSkillResponse) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = item.skillImgUrl,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = item.skillName,
                style = TextStyle(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.skillDescription,
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun HeroDetailPicItem(item: HeroDetailPicResponse) {
    Column(
        modifier = Modifier
            .padding(end = 8.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = item.picUrl,
            contentDescription = null,
            modifier = Modifier.size(width = 300.dp, height = 442.dp)
        )

        Text(
            text = item.picName,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun PreviewHeroDetailCoverItem() {
    val cover = HeroDetailCoverResponse("生存能力", "40")
    Box(modifier = Modifier.background(color = Color.White)) {
        HeroDetailCoverItem(item = cover)
    }
}

@Preview
@Composable
fun PreviewHeroDetailSkillItem() {
    val skill = HeroDetailSkillResponse(
        "穷乎玄间",
        "敖隐显露白龙真身冲上云霄，期间无法攻击、无视地形且不可被选中，最多持续4.5秒(可提前结束)，随后向前俯冲(过程中不可被选中)，对路径上的敌人造成物理伤害和减速，并在落点处对敌人再次造成同等伤害和惧龙效果，持续1秒。 随后敖隐显露真龙雪剑，提升攻速和射程，持续10秒。期间敖隐使用真龙雪剑攻击，此时双剑指会对附近至多两个额外目标攻击。 受到惧龙效果的目标会不由自主的远离敖隐。敖隐飞行期间可以任意调整剑指的元素龙魂。",
        ""
    )
    Box(modifier = Modifier.background(color = Color.White)) {
        HeroDetailSkillItem(item = skill)
    }
}

@Preview
@Composable
fun PreviewHeroDetailPicItem() {
    val pic = HeroDetailPicResponse("凌霄真龙", "")
    Box(modifier = Modifier.background(color = Color.White)) {
        HeroDetailPicItem(item = pic)
    }
}