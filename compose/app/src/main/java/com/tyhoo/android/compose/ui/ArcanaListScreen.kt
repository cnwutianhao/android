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
import com.tyhoo.android.compose.data.ArcanaResponse
import com.tyhoo.android.compose.data.Type
import com.tyhoo.android.compose.viewmodel.ArcanaListViewModel

@Composable
fun ArcanaListScreen(
    modifier: Modifier = Modifier,
    viewModel: ArcanaListViewModel = hiltViewModel()
) {
    val arcanaList by viewModel.arcanaList.observeAsState(initial = emptyList())
    ArcanaListScreen(arcanaList = arcanaList, modifier)
}

@Composable
fun ArcanaListScreen(
    arcanaList: List<ArcanaResponse>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = arcanaList) { arcana ->
            ArcanaItem(arcana)
        }
    }
}

@Composable
fun ArcanaItem(arcana: ArcanaResponse) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // 铭文图
        AsyncImage(
            model = "https://game.gtimg.cn/images/yxzj/img201606/mingwen/${arcana.mingId}.png",
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )

        Column(modifier = Modifier.padding(top = 8.dp, end = 8.dp)) {
            // 铭文名称
            Text(
                text = arcana.mingName,
                style = TextStyle(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 铭文颜色
            val type = when (arcana.mingType) {
                "red" -> Type.RED
                "yellow" -> Type.BLUE
                "blue" -> Type.GREEN
                else -> null
            }
            Text(
                text = stringResource(id = R.string.arcana_type, type?.type ?: ""),
                style = TextStyle(fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 铭文等级
            val grade = when (arcana.mingGrade) {
                "1" -> Grade.ONE
                "2" -> Grade.TWO
                "3" -> Grade.THREE
                "4" -> Grade.FOUR
                "5" -> Grade.FIVE
                else -> null
            }
            Text(
                text = stringResource(id = R.string.arcana_gradle, grade?.grade ?: ""),
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewArcanaItem() {
    val testArcana = ArcanaResponse("1501", "red", "5", "圣人", "<p>法术攻击力+5.3</p>")
    Box(modifier = Modifier.background(color = Color.White)) {
        ArcanaItem(arcana = testArcana)
    }
}