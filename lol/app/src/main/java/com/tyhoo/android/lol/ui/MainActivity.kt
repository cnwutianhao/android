package com.tyhoo.android.lol.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tyhoo.android.lol.domain.Hero
import com.tyhoo.android.lol.ui.theme.LolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HeroesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LolApp {
                HeroesScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun LolApp(content: @Composable () -> Unit) {
    LolTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Composable
fun HeroesScreen(viewModel: HeroesViewModel) {
    val heroes = viewModel.heroes.value
    val error = viewModel.error.value
    val version = viewModel.version.value
    val fileTime = viewModel.fileTime.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Text(text = "英雄列表", fontWeight = FontWeight.Bold)
            Text(text = "Ver.$version ($fileTime)", fontSize = 14.sp)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(heroes) { hero ->
                HeroItem(hero = hero)
            }
        }

        error?.let { message ->
            Text(text = message, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun HeroItem(hero: Hero) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            val avatarUrl = "https://game.gtimg.cn/images/lol/act/img/champion/${hero.alias}.png"

            Image(
                painter = rememberAsyncImagePainter(avatarUrl),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = hero.name + " " + hero.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                HeroRoles(roles = hero.roles)
            }
        }
    }
}

@Composable
fun HeroRoles(roles: List<String>) {
    LazyRow {
        items(roles) { role ->
            val roleMap = mapOf(
                "mage" to "法师",
                "fighter" to "战士",
                "tank" to "坦克",
                "marksman" to "射手",
                "support" to "辅助",
                "assassin" to "刺客"
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = roleMap[role] ?: "未知", fontSize = 14.sp)
                Spacer(Modifier.width(4.dp))
            }
        }
    }
}
