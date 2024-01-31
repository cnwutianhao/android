package com.tyhoo.android.compose

import androidx.annotation.StringRes

sealed class Screen(@StringRes val resourceId: Int, val icon: Int, val route: String) {
    data object HeroList : Screen(R.string.hero_list, R.drawable.hero, "hero_list")
    data object ItemList : Screen(R.string.item_list, R.drawable.item, "item_list")
    data object RuneList : Screen(R.string.rune_list, R.drawable.rune, "rune_list")
}