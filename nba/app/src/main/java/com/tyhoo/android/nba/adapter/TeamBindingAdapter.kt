package com.tyhoo.android.nba.adapter

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.caverock.androidsvg.SVG
import com.tyhoo.android.nba.R
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

@BindingAdapter(value = ["teamsTeamLogoFromTeamAbbr"])
fun bindTeamsTeamLogoFromTeamAbbr(view: ImageView, abbr: String?) {
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
