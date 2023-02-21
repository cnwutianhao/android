package com.tyhoo.android.nba.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.tyhoo.android.nba.adapter.TeamsAdapter
import com.tyhoo.android.nba.databinding.FragmentTeamsBinding
import com.tyhoo.android.nba.viewmodel.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private lateinit var binding: FragmentTeamsBinding

    private var job: Job? = null

    private val viewModel: TeamsViewModel by viewModels()

    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
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
        adapter = TeamsAdapter()
        binding.teamList.addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        binding.teamList.adapter = adapter
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            viewModel.requestData(viewLifecycleOwner, adapter)
        }
    }
}