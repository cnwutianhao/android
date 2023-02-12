package com.tyhoo.android.nba.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.tyhoo.android.nba.adapter.PlayersAdapter
import com.tyhoo.android.nba.databinding.FragmentPlayersBinding
import com.tyhoo.android.nba.viewmodel.PlayersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class PlayersFragment : Fragment() {

    private lateinit var binding: FragmentPlayersBinding

    private var job: Job? = null

    private val viewModel: PlayersViewModel by viewModels()

    private lateinit var adapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
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
        adapter = PlayersAdapter()
        binding.playerList.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        binding.playerList.adapter = adapter
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            viewModel.requestData(viewLifecycleOwner, adapter)
        }
    }
}