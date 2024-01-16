package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tyhoo.android.mvvm.adapter.HeroAdapter
import com.tyhoo.android.mvvm.databinding.FragmentHeroListBinding
import com.tyhoo.android.mvvm.viewmodel.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private lateinit var binding: FragmentHeroListBinding

    private var job: Job? = null

    private val viewModel: HeroListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            val adapter = HeroAdapter()
            binding.heroList.adapter = adapter
            viewModel.requestData(viewLifecycleOwner, adapter)
        }
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}