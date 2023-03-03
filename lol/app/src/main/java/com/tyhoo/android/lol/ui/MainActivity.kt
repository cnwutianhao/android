package com.tyhoo.android.lol.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tyhoo.android.lol.R
import com.tyhoo.android.lol.domain.BottomNavItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val heroesViewModel by viewModels<HeroesViewModel>()
    private val itemsViewModel by viewModels<ItemsViewModel>()
    private val heroViewModel by viewModels<HeroViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LolApp(heroesViewModel, itemsViewModel, heroViewModel) }
    }
}

@Composable
fun LolApp(
    heroesViewModel: HeroesViewModel,
    itemsViewModel: ItemsViewModel,
    heroViewModel: HeroViewModel
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    // 设置状态栏颜色
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem("heroes", "Heroes", R.drawable.lol_heroes),
        BottomNavItem("items", "Items", R.drawable.lol_items)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "heroes"

    // 在Scaffold外层添加一个背景色，以免系统状态栏被Scaffold的背景色遮挡
    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.drawableId),
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = "heroes"
                ) {
                    composable("heroes") {
                        HeroesScreen(viewModel = heroesViewModel, navController = navController)
                    }
                    composable("items") {
                        ItemsScreen(viewModel = itemsViewModel)
                    }
                    composable("hero/{heroId}") { backStackEntry ->
                        val heroId = backStackEntry.arguments?.getString("heroId") ?: ""
                        HeroScreen(viewModel = heroViewModel, heroId = heroId)
                    }
                }
            }
        }
    }
}
