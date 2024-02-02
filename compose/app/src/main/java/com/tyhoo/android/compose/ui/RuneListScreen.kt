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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tyhoo.android.compose.R
import com.tyhoo.android.compose.data.Grade
import com.tyhoo.android.compose.data.RuneResponse
import com.tyhoo.android.compose.data.Type
import com.tyhoo.android.compose.viewmodel.RuneListViewModel

@Composable
fun RuneListScreen(
    modifier: Modifier = Modifier,
    viewModel: RuneListViewModel = hiltViewModel()
) {
    val runeList by viewModel.runeList.observeAsState(initial = emptyList())
    RuneListScreen(runeList = runeList, modifier)
}

@Composable
fun RuneListScreen(
    runeList: List<RuneResponse>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = runeList) { rune ->
            RuneItem(rune)
        }
    }
}

@Composable
fun RuneItem(rune: RuneResponse) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // 铭文图
        AsyncImage(
            model = "https://game.gtimg.cn/images/yxzj/img201606/mingwen/${rune.mingId}.png",
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )

        Column(modifier = Modifier.padding(top = 8.dp, end = 8.dp)) {
            // 铭文名称
            Text(
                text = rune.mingName,
                style = TextStyle(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 铭文颜色
            val type = when (rune.mingType) {
                "red" -> Type.RED
                "yellow" -> Type.BLUE
                "blue" -> Type.GREEN
                else -> null
            }
            Text(
                text = stringResource(id = R.string.rune_type, type?.type ?: ""),
                style = TextStyle(fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 铭文等级
            val grade = when (rune.mingGrade) {
                "1" -> Grade.ONE
                "2" -> Grade.TWO
                "3" -> Grade.THREE
                "4" -> Grade.FOUR
                "5" -> Grade.FIVE
                else -> null
            }
            Text(
                text = stringResource(id = R.string.rune_gradle, grade?.grade ?: ""),
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRuneItem() {
    val testRune = RuneResponse("1501", "red", "5", "圣人", "<p>法术攻击力+5.3</p>")
    Box(modifier = Modifier.background(color = Color.White)) {
        RuneItem(rune = testRune)
    }
}