package com.tyhoo.android.mvvm.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.tyhoo.android.mvvm.R
import com.tyhoo.android.mvvm.data.HeroDetailCoverResponse

@BindingAdapter(value = ["heroAvatarEName"])
fun bindHeroAvatar(view: ImageView, eName: Int?) {
    eName?.let {
        Glide.with(view.context)
            .load("https://game.gtimg.cn/images/yxzj/img201606/heroimg/${it}/${it}-smallskin-1.jpg")
            .into(view)
    }
}

@BindingAdapter(value = ["heroType", "heroType2"])
fun bindHeroType(view: TextView, type: Int?, type2: Int?) {
    type?.let { heroType ->
        val role = Role.entries.toTypedArray().find { it.value == heroType }
        role?.let { heroRole ->
            val roleName = heroRole.type
            view.text = view.context.getString(R.string.hero_type, roleName)

            type2?.let { heroType2 ->
                val role2 = Role.entries.toTypedArray().find { it.value == heroType2 }
                role2?.let { heroRole2 ->
                    val roleName2 = heroRole2.type
                    view.text = view.context.getString(R.string.hero_type2, roleName, roleName2)
                }
            }
        }
    }
}

@BindingAdapter(value = ["heroPay"])
fun bindHeroPay(view: TextView, pay: Int?) {
    if (pay == null) {
        view.text = ""
    }

    pay?.let { heroPay ->
        val payType = Pay.entries.toTypedArray().find { it.value == heroPay }
        payType?.let { type ->
            view.text = view.context.getString(R.string.hero_pay, type.type)
        }
    }
}

@BindingAdapter(value = ["heroDetailSkillImg"])
fun bindHeroDetailSkillImg(view: ImageView, skillImgUrl: String?) {
    skillImgUrl?.let { url ->
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter(value = ["heroDetailPic"])
fun bindHeroDetailPic(view: ImageView, picUrl: String?) {
    picUrl?.let { url ->
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter(value = ["heroDetailProgress"])
fun bindHeroDetailProgress(bar: ProgressBar, cover: HeroDetailCoverResponse?) {
    cover?.let {
        bar.progress = it.bar.toInt()
        when (it.label) {
            "生存能力" -> bar.progressDrawable =
                AppCompatResources.getDrawable(bar.context, R.drawable.progress_bar_blue)

            "攻击伤害" -> bar.progressDrawable =
                AppCompatResources.getDrawable(bar.context, R.drawable.progress_bar_yellow)

            "技能效果" -> bar.progressDrawable =
                AppCompatResources.getDrawable(bar.context, R.drawable.progress_bar_green)

            "上手难度" -> bar.progressDrawable =
                AppCompatResources.getDrawable(bar.context, R.drawable.progress_bar_red)
        }
    }
}

@BindingAdapter(value = ["heroFabMenu"])
fun bindHeroFabMenu(view: View, isShow: LiveData<Boolean>?) {
    isShow?.let {
        if (it.value == true) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}

enum class Role(val value: Int, val type: String) {
    WARRIOR(1, "战士"),
    MAGE(2, "法师"),
    TANK(3, "坦克"),
    ASSASSIN(4, "刺客"),
    MARKSMAN(5, "射手"),
    SUPPORT(6, "辅助")
}

enum class Pay(val value: Int, val type: String) {
    RECOMMEND(10, "本周免费"),
    FREE(11, "新手推荐")
}