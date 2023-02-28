package com.tyhoo.android.lol.domain

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val drawableId: Int
)