package com.tyhoo.android.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tyhoo.android.mvvm.R
import com.tyhoo.android.mvvm.viewmodel.ArcanaListViewModel
import com.tyhoo.android.mvvm.viewmodel.EquipmentListViewModel
import com.tyhoo.android.mvvm.viewmodel.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // ViewModel 与宿主 Activity 绑定
    val heroListViewModel: HeroListViewModel by viewModels()
    val equipmentListViewModel: EquipmentListViewModel by viewModels()
    val arcanaListViewModel: ArcanaListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }
}