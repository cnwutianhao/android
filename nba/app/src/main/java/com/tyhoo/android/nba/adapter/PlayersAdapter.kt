package com.tyhoo.android.nba.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.nba.data.NavToPlayer
import com.tyhoo.android.nba.data.db.PlayerEntity
import com.tyhoo.android.nba.databinding.ListItemPlayersBinding
import com.tyhoo.android.nba.ui.home.HomeFragmentDirections

class PlayersAdapter : ListAdapter<PlayerEntity, ViewHolder>(PlayerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PlayersViewHolder(
            ListItemPlayersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = getItem(position)
        (holder as PlayersViewHolder).bind(player)
    }

    class PlayersViewHolder(
        private val binding: ListItemPlayersBinding
    ) : ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.player?.let { player -> navigateToPlayer(player, view) }
            }
        }

        fun bind(item: PlayerEntity) {
            binding.apply {
                player = item
                executePendingBindings()
            }
        }

        private fun navigateToPlayer(player: PlayerEntity, view: View) {
            val direction = HomeFragmentDirections
                .actionHomeFragmentToPlayerFragment(NavToPlayer(player.playerCode, player.teamCode))
            view.findNavController().navigate(direction)
        }
    }
}

private class PlayerDiffCallback : DiffUtil.ItemCallback<PlayerEntity>() {

    override fun areItemsTheSame(oldItem: PlayerEntity, newItem: PlayerEntity): Boolean {
        return oldItem.playerId == newItem.playerId
    }

    override fun areContentsTheSame(oldItem: PlayerEntity, newItem: PlayerEntity): Boolean {
        return oldItem == newItem
    }
}