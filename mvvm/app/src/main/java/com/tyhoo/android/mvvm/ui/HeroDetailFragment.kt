package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tyhoo.android.mvvm.adapter.HeroDetailCoverAdapter
import com.tyhoo.android.mvvm.adapter.HeroDetailPicAdapter
import com.tyhoo.android.mvvm.adapter.HeroDetailSkillAdapter
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
            val coverAdapter = HeroDetailCoverAdapter()
            val skillAdapter = HeroDetailSkillAdapter()
            val picAdapter = HeroDetailPicAdapter()
            binding.heroDetailCoverList.adapter = coverAdapter
            binding.heroDetailSkillList.adapter = skillAdapter
            val picLayoutManager = LinearLayoutManager(requireContext())
            picLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            binding.heroDetailPicList.layoutManager = picLayoutManager
            binding.heroDetailPicList.adapter = picAdapter
            viewModel.requestData(
                viewLifecycleOwner, args.heroIdName, coverAdapter, skillAdapter, picAdapter
            )
        }
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}