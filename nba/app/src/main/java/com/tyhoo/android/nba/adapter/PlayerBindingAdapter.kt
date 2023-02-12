package com.tyhoo.android.nba.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tyhoo.android.nba.R
import com.tyhoo.android.nba.util.GlideApp

@BindingAdapter(value = ["playersPlayerAvatarFromPlayerId"])
fun bindPlayersPlayerAvatarFromPlayerId(view: ImageView, playerId: String?) {
    // 球员头像请求地址：https://china.nba.cn/media/img/players/head/260x190/2544.png
    playerId?.let { id ->
        val avatarUrl = String
            .format(view.context.getString(R.string.players_player_avatar_url), id)

        GlideApp.with(view.context)
            .load(avatarUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}