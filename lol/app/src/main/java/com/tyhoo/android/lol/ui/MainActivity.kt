package com.tyhoo.android.lol.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tyhoo.android.lol.R
import com.tyhoo.android.lol.data.BottomNavItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HeroesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LolApp(viewModel) }
    }
}

@Composable
fun LolApp(viewModel: HeroesViewModel) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem("heroes", "Heroes", R.drawable.lol_heroes),
        BottomNavItem("items", "Items", R.drawable.lol_items)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "heroes"

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
                    HeroesScreen(viewModel = viewModel)
                }
                composable("items") {
                    ItemsScreen()
                }
            }
        }
    }
}
