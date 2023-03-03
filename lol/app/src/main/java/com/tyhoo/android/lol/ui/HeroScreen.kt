package com.tyhoo.android.lol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun HeroScreen(viewModel: HeroViewModel, heroId: String) {

    viewModel.loadHero(heroId)

    val heroInfo = viewModel.heroInfo.value
    val heroSkins = viewModel.heroSkins.value
    val heroSpells = viewModel.heroSpells.value
    val version = viewModel.version.value
    val fileTime = viewModel.fileTime.value
    val error = viewModel.error.value
    val imageUrl = "https://game.gtimg.cn/images/lol/act/img/skin/big${heroId}000.jpg"

    Column(modifier = Modifier.fillMaxWidth()) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl
                ),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .aspectRatio(49f / 25f)
                    .fillMaxWidth()
            )

            heroInfo?.let { info ->
                Text(
                    text = info.name + " " + info.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 16.dp)
                )
            }
        }

        heroInfo?.let { info ->
            Text(
                text = info.shortBio,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
