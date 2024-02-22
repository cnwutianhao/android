package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.ArcanaResponse
import com.tyhoo.android.mvvm.databinding.ListItemArcanaBinding

class ArcanaAdapter :
    ListAdapter<ArcanaResponse, ArcanaAdapter.ArcanaViewHolder>(ArcanaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArcanaViewHolder {
        return ArcanaViewHolder(
            ListItemArcanaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArcanaViewHolder, position: Int) {
        val arcana = getItem(position)
        holder.bind(arcana)
    }

    inner class ArcanaViewHolder(
        private val binding: ListItemArcanaBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: ArcanaResponse) {
            binding.apply {
                arcana = item
                executePendingBindings()
            }
        }
    }
}

private class ArcanaDiffCallback : DiffUtil.ItemCallback<ArcanaResponse>() {
    override fun areItemsTheSame(oldItem: ArcanaResponse, newItem: ArcanaResponse): Boolean {
        return oldItem.mingId == newItem.mingId
    }

    override fun areContentsTheSame(oldItem: ArcanaResponse, newItem: ArcanaResponse): Boolean {
        return oldItem == newItem
    }
}