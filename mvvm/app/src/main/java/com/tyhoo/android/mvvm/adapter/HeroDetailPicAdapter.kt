package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.HeroDetailPicResponse
import com.tyhoo.android.mvvm.databinding.ListItemHeroDetailPicBinding

class HeroDetailPicAdapter :
    ListAdapter<HeroDetailPicResponse, HeroDetailPicAdapter.HeroDetailPicViewHolder>(
        HeroDetailPicDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroDetailPicViewHolder {
        return HeroDetailPicViewHolder(
            ListItemHeroDetailPicBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroDetailPicViewHolder, position: Int) {
        val pic = getItem(position)
        holder.bind(pic)
    }

    inner class HeroDetailPicViewHolder(
        private val binding: ListItemHeroDetailPicBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: HeroDetailPicResponse) {
            binding.apply {
                pic = item
                executePendingBindings()
            }
        }
    }
}

private class HeroDetailPicDiffCallback : DiffUtil.ItemCallback<HeroDetailPicResponse>() {
    override fun areItemsTheSame(
        oldItem: HeroDetailPicResponse, newItem: HeroDetailPicResponse
    ): Boolean {
        return oldItem.picName == newItem.picName
    }

    override fun areContentsTheSame(
        oldItem: HeroDetailPicResponse, newItem: HeroDetailPicResponse
    ): Boolean {
        return oldItem == newItem
    }
}