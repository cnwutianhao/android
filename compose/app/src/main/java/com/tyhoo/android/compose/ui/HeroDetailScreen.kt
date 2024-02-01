package com.tyhoo.android.compose.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tyhoo.android.compose.viewmodel.HeroDetailViewModel

@Composable
fun HeroDetailScreen(
    viewModel: HeroDetailViewModel = hiltViewModel()
) {
    Text(text = "Hero Detail Screen")
}