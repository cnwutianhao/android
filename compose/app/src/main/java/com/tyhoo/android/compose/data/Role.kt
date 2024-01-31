package com.tyhoo.android.compose.data

enum class Role(val value: Int, val type: String) {
    WARRIOR(1, "战士"),
    MAGE(2, "法师"),
    TANK(3, "坦克"),
    ASSASSIN(4, "刺客"),
    MARKSMAN(5, "射手"),
    SUPPORT(6, "辅助")
}