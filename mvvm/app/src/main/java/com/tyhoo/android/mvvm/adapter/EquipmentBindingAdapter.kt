package com.tyhoo.android.mvvm.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tyhoo.android.mvvm.R

@BindingAdapter(value = ["equipmentImg"])
fun bindEquipmentImg(view: ImageView, itemId: Int?) {
    itemId?.let { equipmentId ->
        Glide.with(view.context)
            .load("https://game.gtimg.cn/images/yxzj/img201606/itemimg/${equipmentId}.jpg")
            .into(view)
    }
}

@BindingAdapter(value = ["equipmentPrice"])
fun bindEquipmentPrice(view: TextView, totalPrice: Int?) {
    totalPrice?.let { price ->
        view.text = view.context.getString(R.string.equipment_price, price.toString())
    }
}