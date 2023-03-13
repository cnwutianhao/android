package com.tyhoo.android.lol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tyhoo.android.lol.domain.Item

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel,
    itemViewModel: ItemViewModel,
    navController: NavController
) {

    val items = viewModel.items.value
    val error = viewModel.error.value
    val version = viewModel.version.value
    val fileTime = viewModel.fileTime.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Text(text = "装备列表（${items.size}个）", fontWeight = FontWeight.Bold)
            Text(text = "Ver.$version ($fileTime)", fontSize = 14.sp)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(items) { item ->
                ItemItem(item = item, onItemClick = { selectedItem ->
                    // 如果数据不为空，清空上一次的数据
                    itemViewModel.selectedItem.value?.let {
                        itemViewModel.selectedItem.value = null
                    }
                    itemViewModel.selectedItem.value = selectedItem
                    navController.navigate("item")
                })
            }
        }

        error?.let { message ->
            Text(text = message, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun ItemItem(item: Item, onItemClick: (Item) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(item) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.iconPath),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.plaintext,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
