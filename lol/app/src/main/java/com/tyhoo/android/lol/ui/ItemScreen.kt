package com.tyhoo.android.lol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tyhoo.android.lol.domain.Item

@Composable
fun ItemScreen(item: Item?) {

    item?.let { data ->
        val name = data.name
        val iconPath = data.iconPath
        val price = data.price
        val maps = data.maps
        val plaintext = data.plaintext

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(iconPath),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "物品：$name",
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .align(Alignment.Start)
            )

            Text(
                text = "金额：$price",
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
                    .align(Alignment.Start)
            )

            Text(
                text = "描述：$plaintext",
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
                    .align(Alignment.Start)
            )

            Text(
                text = "地图：${maps.joinToString("，")}",
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
                    .align(Alignment.Start)
            )
        }
    }
}