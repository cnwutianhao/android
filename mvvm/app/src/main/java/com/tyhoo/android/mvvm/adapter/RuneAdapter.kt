package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.RuneResponse
import com.tyhoo.android.mvvm.databinding.ListItemRuneBinding

class RuneAdapter : ListAdapter<RuneResponse, RuneAdapter.RuneViewHolder>(RuneDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuneViewHolder {
        return RuneViewHolder(
            ListItemRuneBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RuneViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class RuneViewHolder(
        private val binding: ListItemRuneBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: RuneResponse) {
            binding.apply {
                rune = item
                executePendingBindings()
            }
        }
    }
}

private class RuneDiffCallback : DiffUtil.ItemCallback<RuneResponse>() {
    override fun areItemsTheSame(oldItem: RuneResponse, newItem: RuneResponse): Boolean {
        return oldItem.mingId == newItem.mingId
    }

    override fun areContentsTheSame(oldItem: RuneResponse, newItem: RuneResponse): Boolean {
        return oldItem == newItem
    }
}