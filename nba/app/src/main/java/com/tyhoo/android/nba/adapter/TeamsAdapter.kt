package com.tyhoo.android.nba.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.nba.data.db.TeamEntity
import com.tyhoo.android.nba.databinding.ListItemTeamsBinding

class TeamsAdapter : ListAdapter<TeamEntity, ViewHolder>(TeamDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TeamsViewHolder(
                ListItemTeamsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = getItem(position)
        (holder as TeamsViewHolder).bind(team)
    }

    class TeamsViewHolder(
            private val binding: ListItemTeamsBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: TeamEntity) {
            binding.apply {
                team = item
                executePendingBindings()
            }
        }
    }
}

private class TeamDiffCallback : DiffUtil.ItemCallback<TeamEntity>() {

    override fun areItemsTheSame(oldItem: TeamEntity, newItem: TeamEntity): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: TeamEntity, newItem: TeamEntity): Boolean {
        return oldItem == newItem
    }
}