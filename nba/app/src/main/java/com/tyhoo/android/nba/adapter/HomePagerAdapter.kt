package com.tyhoo.android.nba.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tyhoo.android.nba.base.PLAYERS_PAGE_INDEX
import com.tyhoo.android.nba.base.TEAMS_PAGE_INDEX
import com.tyhoo.android.nba.ui.player.PlayersFragment
import com.tyhoo.android.nba.ui.team.TeamsFragment

class HomePagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PLAYERS_PAGE_INDEX to { PlayersFragment() },
        TEAMS_PAGE_INDEX to { TeamsFragment() }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}