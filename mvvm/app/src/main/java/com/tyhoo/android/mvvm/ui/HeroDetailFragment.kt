package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.tyhoo.android.mvvm.databinding.FragmentHeroDetailBinding
import com.tyhoo.android.mvvm.viewmodel.HeroDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailBinding

    private val args: HeroDetailFragmentArgs by navArgs()

    private var job: Job? = null

    private val viewModel by viewModels<HeroDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.requestData(viewLifecycleOwner, args.heroIdName)
        }
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}