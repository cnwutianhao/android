package com.tyhoo.android.nba.adapter

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.caverock.androidsvg.SVG
import com.tyhoo.android.nba.R
import com.tyhoo.android.nba.util.GlideApp
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

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

@BindingAdapter(value = ["playerBackgroundFromTeamCode"])
fun bindPlayerBackgroundFromTeamCode(view: View, code: String?) {
    code?.let {
        val colorsMap = mapOf(
            "hawks" to R.color.hawks,
            "celtics" to R.color.celtics,
            "nets" to R.color.nets,
            "hornets" to R.color.hornets,
            "bulls" to R.color.bulls,
            "cavaliers" to R.color.cavaliers,
            "pistons" to R.color.pistons,
            "pacers" to R.color.pacers,
            "heat" to R.color.heat,
            "bucks" to R.color.bucks,
            "knicks" to R.color.knicks,
            "magic" to R.color.magic,
            "sixers" to R.color.sixers,
            "raptors" to R.color.raptors,
            "wizards" to R.color.wizards,
            "mavericks" to R.color.mavericks,
            "nuggets" to R.color.nuggets,
            "warriors" to R.color.warriors,
            "rockets" to R.color.rockets,
            "clippers" to R.color.clippers,
            "lakers" to R.color.lakers,
            "grizzlies" to R.color.grizzlies,
            "timberwolves" to R.color.timberwolves,
            "pelicans" to R.color.pelicans,
            "thunder" to R.color.thunder,
            "suns" to R.color.suns,
            "blazers" to R.color.blazers,
            "kings" to R.color.kings,
            "spurs" to R.color.spurs,
            "jazz" to R.color.jazz
        )
        val color = colorsMap[code] ?: R.color.white
        view.setBackgroundResource(color)
    }
}

@BindingAdapter(value = ["playerAvatarFromPlayerId"])
fun bindPlayerAvatarFromPlayerId(view: ImageView, playerId: String?) {
    // https://china.nba.cn/media/img/players/head/260x190/2544.png
    val imageUrl = "https://china.nba.cn/media/img/players/head/260x190/$playerId.png"

    GlideApp.with(view.context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}

@BindingAdapter(value = ["playerTeamLogoFromAbbr"])
fun bindPlayerTeamLogoFromAbbr(view: ImageView, abbr: String?) {
    abbr?.let {
        // 球队Logo请求地址：https://china.nba.cn/media/img/teams/logos/LAL_logo.svg
        val logoUrl = String
            .format(view.context.getString(R.string.teams_team_logo_url), it)

        CoroutineScope(Job()).launch {
            withContext(Dispatchers.IO) {
                val url = URL(logoUrl)
                val urlConnection = url.openConnection() as HttpURLConnection
                val inputStream = urlConnection.inputStream
                val svg = SVG.getFromInputStream(inputStream)

                withContext(Dispatchers.Main) {
                    val drawable = PictureDrawable(svg.renderToPicture())
                    view.setImageDrawable(drawable)
                }
            }
        }
    }
}