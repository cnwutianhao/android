package com.tyhoo.android.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tyhoo.android.compose.Screen

@Composable
fun HomeScreen() {
    val items = listOf(
        Screen.HeroList,
        Screen.ItemList,
        Screen.RuneList
    )

    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(modifier = Modifier.height(68.dp)) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.resourceId.toString(),
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(text = stringResource(id = item.resourceId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }, containerColor = Color.Transparent) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HeroList.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.HeroList.route) {
                HeroListScreen(onHeroClick = { hero ->
                    navController.navigate(
                        Screen.HeroDetail.createRoute(heroIdName = hero.idName)
                    )
                }, modifier = Modifier.fillMaxSize())
            }
            composable(Screen.ItemList.route) { ItemListScreen(modifier = Modifier.fillMaxSize()) }
            composable(Screen.RuneList.route) { RuneListScreen(modifier = Modifier.fillMaxSize()) }
            composable(
                route = Screen.HeroDetail.route,
                arguments = Screen.HeroDetail.navArguments
            ) {
                HeroDetailScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}