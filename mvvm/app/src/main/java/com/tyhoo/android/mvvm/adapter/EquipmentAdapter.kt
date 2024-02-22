package com.tyhoo.android.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tyhoo.android.mvvm.data.EquipmentResponse
import com.tyhoo.android.mvvm.databinding.ListItemEquipmentBinding

class EquipmentAdapter :
    ListAdapter<EquipmentResponse, EquipmentAdapter.EquipmentViewHolder>(EquipmentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        return EquipmentViewHolder(
            ListItemEquipmentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val equipment = getItem(position)
        holder.bind(equipment)
    }

    inner class EquipmentViewHolder(
        private val binding: ListItemEquipmentBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: EquipmentResponse) {
            binding.apply {
                equipment = item
                executePendingBindings()
            }
        }
    }
}

private class EquipmentDiffCallback : DiffUtil.ItemCallback<EquipmentResponse>() {
    override fun areItemsTheSame(
        oldItem: EquipmentResponse, newItem: EquipmentResponse
    ): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(
        oldItem: EquipmentResponse, newItem: EquipmentResponse
    ): Boolean {
        return oldItem == newItem
    }
}