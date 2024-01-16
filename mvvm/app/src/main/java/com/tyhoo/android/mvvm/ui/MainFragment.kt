package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.tyhoo.android.mvvm.R
import com.tyhoo.android.mvvm.base.TAG
import com.tyhoo.android.mvvm.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_hero -> {
                    Log.d(TAG, "英雄")
                    navController.navigate(R.id.action_to_hero_list)
                }

                R.id.menu_item -> {
                    Log.d(TAG, "装备")
                    navController.navigate(R.id.action_to_item_list)
                }

                R.id.menu_rune -> {
                    Log.d(TAG, "铭文")
                    navController.navigate(R.id.action_to_rune_list)
                }

                else -> {}
            }

            true
        }
    }
}