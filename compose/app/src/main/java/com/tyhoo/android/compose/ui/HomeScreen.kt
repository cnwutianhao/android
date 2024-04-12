package com.tyhoo.android.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tyhoo.android.compose.Screen

@Composable
fun HomeScreen() {
    val items = listOf(
        Screen.HeroList,
        Screen.EquipmentList,
        Screen.ArcanaList
    )

    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(
            items = items,
            navController = navController
        )
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.HeroList.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.HeroList.route) {
                HeroListScreen(onHeroClick = { hero ->
                    navController.navigate(
                        Screen.HeroDetail.createRoute(heroIdName = hero.idName)
                    )
                }, modifier = Modifier.fillMaxSize())
            }
            composable(Screen.EquipmentList.route) { EquipmentListScreen(modifier = Modifier.fillMaxSize()) }
            composable(Screen.ArcanaList.route) { ArcanaListScreen(modifier = Modifier.fillMaxSize()) }
            composable(
                route = Screen.HeroDetail.route,
                arguments = Screen.HeroDetail.navArguments
            ) {
                HeroDetailScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun BottomNavigationBar(items: List<Screen>, navController: NavHostController) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedItem = index
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.resourceId.toString(),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = stringResource(id = item.resourceId)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    val items = listOf(
        Screen.HeroList,
        Screen.EquipmentList,
        Screen.ArcanaList
    )
    BottomNavigationBar(items, rememberNavController())
}