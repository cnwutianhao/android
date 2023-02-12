package com.tyhoo.android.nba.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tyhoo.android.nba.R
import com.tyhoo.android.nba.adapter.HomePagerAdapter
import com.tyhoo.android.nba.base.PLAYERS_PAGE_INDEX
import com.tyhoo.android.nba.base.TEAMS_PAGE_INDEX
import com.tyhoo.android.nba.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomePagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            PLAYERS_PAGE_INDEX -> R.drawable.players_tab_selector
            TEAMS_PAGE_INDEX -> R.drawable.teams_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PLAYERS_PAGE_INDEX -> getString(R.string.home_tab_players)
            TEAMS_PAGE_INDEX -> getString(R.string.home_tab_teams)
            else -> null
        }
    }
}