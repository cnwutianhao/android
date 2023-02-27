package com.tyhoo.android.lol.data

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val drawableId: Int
)