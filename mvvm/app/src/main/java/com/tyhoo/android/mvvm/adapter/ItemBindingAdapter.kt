package com.tyhoo.android.mvvm.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tyhoo.android.mvvm.R

@BindingAdapter(value = ["itemImg"])
fun bindItemImg(view: ImageView, itemId: Int?) {
    itemId?.let {
        Glide.with(view.context)
            .load("https://game.gtimg.cn/images/yxzj/img201606/itemimg/${it}.jpg")
            .into(view)
    }
}

@BindingAdapter(value = ["itemPrice"])
fun bindItemPrice(view: TextView, totalPrice: Int?) {
    totalPrice?.let {
        view.text = view.context.getString(R.string.item_price, it.toString())
    }
}