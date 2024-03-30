package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tyhoo.android.mvvm.adapter.ArcanaAdapter
import com.tyhoo.android.mvvm.base.ARCANA_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.ARCANA_LIST_OFFSET
import com.tyhoo.android.mvvm.databinding.FragmentArcanaListBinding
import com.tyhoo.android.mvvm.viewmodel.ArcanaListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class ArcanaListFragment : Fragment() {

    private lateinit var binding: FragmentArcanaListBinding

    private var job: Job? = null

    private val viewModel: ArcanaListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArcanaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            val adapter = ArcanaAdapter()
            binding.arcanaList.adapter = adapter
            viewModel.requestData(viewLifecycleOwner, binding.arcanaList, adapter)
        }
    }

    override fun onDestroyView() {
        // 页面销毁时记录列表滑动的位置
        val layoutManager = binding.arcanaList.layoutManager as LinearLayoutManager
        val findFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val firstVisibleView = layoutManager.findViewByPosition(findFirstVisibleItemPosition)
        val offset = firstVisibleView?.top ?: 0
        ARCANA_LIST_FIRST_VISIBLE_ITEM_POSITION = findFirstVisibleItemPosition
        ARCANA_LIST_OFFSET = offset

        job?.cancel()
        super.onDestroyView()
    }
}