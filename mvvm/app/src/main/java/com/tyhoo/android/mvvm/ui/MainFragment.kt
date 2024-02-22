package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.tyhoo.android.mvvm.R
import com.tyhoo.android.mvvm.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            val currentDestinationId = navController.currentDestination?.id

            when (item.itemId) {
                R.id.menu_hero -> if (currentDestinationId != R.id.hero_list_fragment) {
                    navController.navigate(R.id.action_to_hero_list)
                }

                R.id.menu_equipment -> if (currentDestinationId != R.id.equipment_list_fragment) {
                    navController.navigate(R.id.action_to_equipment_list)
                }

                R.id.menu_arcana -> if (currentDestinationId != R.id.arcana_list_fragment) {
                    navController.navigate(R.id.action_to_arcana_list)
                }

                else -> {}
            }

            true
        }
    }
}