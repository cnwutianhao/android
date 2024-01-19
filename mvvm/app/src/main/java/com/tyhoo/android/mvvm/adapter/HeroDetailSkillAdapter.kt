package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.HeroDetailSkillResponse
import com.tyhoo.android.mvvm.databinding.ListItemHeroDetailSkillBinding

class HeroDetailSkillAdapter :
    ListAdapter<HeroDetailSkillResponse, HeroDetailSkillAdapter.HeroDetailSkillViewHolder>(
        HeroDetailSkillDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroDetailSkillViewHolder {
        return HeroDetailSkillViewHolder(
            ListItemHeroDetailSkillBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroDetailSkillViewHolder, position: Int) {
        val skill = getItem(position)
        holder.bind(skill)
    }

    inner class HeroDetailSkillViewHolder(
        private val binding: ListItemHeroDetailSkillBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: HeroDetailSkillResponse) {
            binding.apply {
                skill = item
                executePendingBindings()
            }
        }
    }
}

private class HeroDetailSkillDiffCallback : DiffUtil.ItemCallback<HeroDetailSkillResponse>() {
    override fun areItemsTheSame(
        oldItem: HeroDetailSkillResponse, newItem: HeroDetailSkillResponse
    ): Boolean {
        return oldItem.skillName == newItem.skillName
    }

    override fun areContentsTheSame(
        oldItem: HeroDetailSkillResponse, newItem: HeroDetailSkillResponse
    ): Boolean {
        return oldItem == newItem
    }
}