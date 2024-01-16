package com.tyhoo.android.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tyhoo.android.mvvm.R
import com.tyhoo.android.mvvm.util.LayoutUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 沉浸式状态栏、导航栏
        LayoutUtils.setImmersive(window, findViewById(R.id.root_layout))
    }
}