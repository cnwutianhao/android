package com.tyhoo.android.compose

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    @StringRes val resourceId: Int,
    val icon: Int,
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object HeroList : Screen(R.string.hero_list, R.drawable.hero, "hero_list")

    data object ItemList : Screen(R.string.item_list, R.drawable.item, "item_list")

    data object RuneList : Screen(R.string.rune_list, R.drawable.rune, "rune_list")

    data object HeroDetail : Screen(
        resourceId = R.string.hero_detail,
        icon = 0,
        route = "hero_detail/{heroIdName}",
        navArguments = listOf(navArgument("heroIdName") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(heroIdName: String) = "hero_detail/${heroIdName}"
    }
}