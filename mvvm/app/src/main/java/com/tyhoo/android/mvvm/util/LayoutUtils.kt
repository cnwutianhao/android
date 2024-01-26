package com.tyhoo.android.mvvm.util

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

object LayoutUtils {

    /**
     * 沉浸式状态栏、导航栏
     */
    fun setImmersive(window: Window, view: View) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = Color.TRANSPARENT
        }

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val statusBarWindow = insets
                .getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.setPadding(0, statusBarWindow.top, 0, 0)
            insets
        }

        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }
}