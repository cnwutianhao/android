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
import com.tyhoo.android.compose.data.EquipmentResponse
import com.tyhoo.android.compose.viewmodel.EquipmentListViewModel

@Composable
fun EquipmentListScreen(
    modifier: Modifier = Modifier,
    viewModel: EquipmentListViewModel = hiltViewModel()
) {
    val equipmentList by viewModel.equipmentList.observeAsState(initial = emptyList())
    EquipmentListScreen(equipmentList = equipmentList, modifier)
}

@Composable
fun EquipmentListScreen(
    equipmentList: List<EquipmentResponse>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = equipmentList) { equipment ->
            EquipmentItem(equipment)
        }
    }
}

@Composable
fun EquipmentItem(equipment: EquipmentResponse) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // 装备图
        AsyncImage(
            model = "https://game.gtimg.cn/images/yxzj/img201606/itemimg/${equipment.itemId}.jpg",
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )

        Column(modifier = Modifier.padding(top = 8.dp, end = 8.dp)) {
            // 装备名称
            Text(
                text = equipment.itemName,
                style = TextStyle(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 装备总价
            Text(
                text = stringResource(
                    id = R.string.equipment_price, equipment.totalPrice.toString()
                ),
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewEquipmentItem() {
    val testEquipment =
        EquipmentResponse(1111, "铁剑", 1, 150, 250, "<p>+20物理攻击</p>", "<p>+20物理攻击</p>")
    Box(modifier = Modifier.background(color = Color.White)) {
        EquipmentItem(equipment = testEquipment)
    }
}