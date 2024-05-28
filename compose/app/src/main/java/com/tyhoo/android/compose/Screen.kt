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
    data object HeroList :
        Screen(R.string.hero_list, R.drawable.hero, "hero_list")

    data object EquipmentList :
        Screen(R.string.equipment_list, R.drawable.equipment, "equipment_list")

    data object ArcanaList :
        Screen(R.string.arcana_list, R.drawable.arcana, "arcana_list")

    data object HeroDetail : Screen(
        resourceId = R.string.hero_detail,
        icon = R.drawable.hero,
        route = "hero_detail/{heroIdName}",
        navArguments = listOf(navArgument("heroIdName") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(heroIdName: String) = "hero_detail/${heroIdName}"
    }
}