package com.tyhoo.android.nba.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.tyhoo.android.nba.databinding.FragmentPlayerBinding
import com.tyhoo.android.nba.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding

    private val args: PlayerFragmentArgs by navArgs()

    private var job: Job? = null

    private val viewModel by viewModels<PlayerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Bind layout views to Architecture Components.
        // https://developer.android.com/topic/libraries/data-binding/architecture
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        requestData()
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }

    private fun initUI() {
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            viewModel.requestData(viewLifecycleOwner, args.navToPlayer)
        }
    }
}