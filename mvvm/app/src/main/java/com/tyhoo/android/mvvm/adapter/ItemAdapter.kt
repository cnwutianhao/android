package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.ItemResponse
import com.tyhoo.android.mvvm.databinding.ListItemItemBinding

class ItemAdapter : ListAdapter<ItemResponse, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(
        private val binding: ListItemItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: ItemResponse) {
            binding.apply {
                equip = item
                executePendingBindings()
            }
        }
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<ItemResponse>() {
    override fun areItemsTheSame(oldItem: ItemResponse, newItem: ItemResponse): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ItemResponse, newItem: ItemResponse): Boolean {
        return oldItem == newItem
    }
}