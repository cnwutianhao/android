package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.HeroResponse
import com.tyhoo.android.mvvm.databinding.ListItemHeroBinding
import com.tyhoo.android.mvvm.ui.HeroListFragmentDirections

class HeroAdapter : ListAdapter<HeroResponse, HeroAdapter.HeroViewHolder>(HeroDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            ListItemHeroBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.bind(hero)
    }

    inner class HeroViewHolder(
        private val binding: ListItemHeroBinding
    ) : ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.hero?.let { hero ->
                    navigateToHeroDetail(hero, it)
                }
            }
        }

        private fun navigateToHeroDetail(hero: HeroResponse, view: View) {
            val direction = HeroListFragmentDirections
                .actionHeroListFragmentToHeroDetailFragment(hero.idName)
            view.findNavController().navigate(direction)
        }

        fun bind(item: HeroResponse) {
            binding.apply {
                hero = item
                executePendingBindings()
            }
        }
    }
}

private class HeroDiffCallback : DiffUtil.ItemCallback<HeroResponse>() {
    override fun areItemsTheSame(oldItem: HeroResponse, newItem: HeroResponse): Boolean {
        return oldItem.eName == newItem.eName
    }

    override fun areContentsTheSame(oldItem: HeroResponse, newItem: HeroResponse): Boolean {
        return oldItem == newItem
    }
}