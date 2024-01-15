package com.tyhoo.android.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tyhoo.android.mvvm.util.LayoutUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 沉浸式状态栏、导航栏
        LayoutUtils.setImmersive(window, findViewById(R.id.root_layout))
    }
}