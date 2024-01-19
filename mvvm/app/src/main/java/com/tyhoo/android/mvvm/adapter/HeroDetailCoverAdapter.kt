package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.HeroDetailCoverResponse
import com.tyhoo.android.mvvm.databinding.ListItemHeroDetailCoverBinding

class HeroDetailCoverAdapter :
    ListAdapter<HeroDetailCoverResponse, HeroDetailCoverAdapter.HeroDetailCoverViewHolder>(
        HeroDetailCoverDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroDetailCoverViewHolder {
        return HeroDetailCoverViewHolder(
            ListItemHeroDetailCoverBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroDetailCoverViewHolder, position: Int) {
        val cover = getItem(position)
        holder.bind(cover)
    }

    inner class HeroDetailCoverViewHolder(
        private val binding: ListItemHeroDetailCoverBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: HeroDetailCoverResponse) {
            binding.apply {
                cover = item
                executePendingBindings()
            }
        }
    }
}

private class HeroDetailCoverDiffCallback : DiffUtil.ItemCallback<HeroDetailCoverResponse>() {
    override fun areItemsTheSame(
        oldItem: HeroDetailCoverResponse, newItem: HeroDetailCoverResponse
    ): Boolean {
        return oldItem.label == newItem.label
    }

    override fun areContentsTheSame(
        oldItem: HeroDetailCoverResponse, newItem: HeroDetailCoverResponse
    ): Boolean {
        return oldItem == newItem
    }
}