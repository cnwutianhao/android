package com.tyhoo.android.mvvm.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tyhoo.android.mvvm.R

@BindingAdapter(value = ["arcanaImg"])
fun bindArcanaImg(view: ImageView, mingId: String?) {
    mingId?.let { arcanaId ->
        Glide.with(view.context)
            .load("https://game.gtimg.cn/images/yxzj/img201606/mingwen/${arcanaId}.png")
            .into(view)
    }
}

@BindingAdapter(value = ["arcanaType"])
fun bindArcanaType(view: TextView, mingType: String?) {
    mingType?.let { arcanaType ->
        val type = Type.entries.toTypedArray().find { it.value == arcanaType }
        type?.let {
            view.text = view.context.getString(R.string.arcana_type, it.type)
        }
    }
}

@BindingAdapter(value = ["arcanaGrade"])
fun bindArcanaGrade(view: TextView, mingGrade: String?) {
    mingGrade?.let { arcanaGrade ->
        val grade = Grade.entries.toTypedArray().find { it.value == arcanaGrade }
        grade?.let {
            view.text = view.context.getString(R.string.arcana_gradle, it.grade)
        }
    }
}

enum class Type(val value: String, val type: String) {
    RED("red", "红"),
    BLUE("yellow", "蓝"),
    GREEN("blue", "绿")
}

enum class Grade(val value: String, val grade: String) {
    ONE("1", "一级"),
    TWO("2", "二级"),
    THREE("3", "三级"),
    FOUR("4", "四级"),
    FIVE("5", "五级")
}