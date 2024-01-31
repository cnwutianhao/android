package com.tyhoo.android.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tyhoo.android.compose.R
import com.tyhoo.android.compose.viewmodel.HeroListViewModel
import com.tyhoo.android.compose.data.HeroResponse
import com.tyhoo.android.compose.data.Pay
import com.tyhoo.android.compose.data.Role

@Composable
fun HeroListScreen(
    modifier: Modifier = Modifier,
    viewModel: HeroListViewModel = hiltViewModel()
) {
    val heroList by viewModel.heroList.observeAsState(initial = emptyList())
    HeroListScreen(heroList = heroList, modifier)
}

@Composable
fun HeroListScreen(
    heroList: List<HeroResponse>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = heroList) { hero ->
            HeroItem(hero)
        }
    }
}

@Composable
fun HeroItem(hero: HeroResponse) {
    Row {
        // 英雄头像
        AsyncImage(
            model = "https://game.gtimg.cn/images/yxzj/img201606/heroimg/${hero.eName}/${hero.eName}-smallskin-1.jpg",
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        Column(modifier = Modifier.padding(top = 8.dp, end = 8.dp)) {
            // 英雄名称
            Text(
                text = hero.cName,
                style = TextStyle(fontSize = 20.sp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 英雄职业
            if (hero.heroType2 == null) {
                val role = when (hero.heroType) {
                    1 -> Role.WARRIOR
                    2 -> Role.MAGE
                    3 -> Role.TANK
                    4 -> Role.ASSASSIN
                    5 -> Role.MARKSMAN
                    6 -> Role.SUPPORT
                    else -> null
                }
                Text(
                    text = "游戏职业：${role?.type}",
                    style = TextStyle(fontSize = 14.sp)
                )
            } else {
                val role1 = when (hero.heroType) {
                    1 -> Role.WARRIOR
                    2 -> Role.MAGE
                    3 -> Role.TANK
                    4 -> Role.ASSASSIN
                    5 -> Role.MARKSMAN
                    6 -> Role.SUPPORT
                    else -> null
                }
                val role2 = when (hero.heroType2) {
                    1 -> Role.WARRIOR
                    2 -> Role.MAGE
                    3 -> Role.TANK
                    4 -> Role.ASSASSIN
                    5 -> Role.MARKSMAN
                    6 -> Role.SUPPORT
                    else -> null
                }
                Text(
                    text = "游戏职业：${role1?.type}，${role2?.type}",
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            hero.payType?.let { pay ->
                val p = when (pay) {
                    10 -> Pay.RECOMMEND
                    11 -> Pay.FREE
                    else -> null
                }
                // 综合信息
                Text(
                    text = "综合信息：${p?.type}",
                    style = TextStyle(fontSize = 14.sp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHeroItem() {
    Row(modifier = Modifier.background(color = Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        Column(modifier = Modifier.padding(top = 8.dp, end = 8.dp)) {
            Text(
                text = "廉颇",
                style = TextStyle(fontSize = 20.sp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "游戏职业：坦克",
                style = TextStyle(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "综合信息：新手推荐",
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}