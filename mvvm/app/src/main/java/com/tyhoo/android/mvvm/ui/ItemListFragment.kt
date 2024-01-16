package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tyhoo.android.mvvm.adapter.ItemAdapter
import com.tyhoo.android.mvvm.base.ITEM_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.ITEM_LIST_OFFSET
import com.tyhoo.android.mvvm.databinding.FragmentItemListBinding
import com.tyhoo.android.mvvm.viewmodel.ItemListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding

    private var job: Job? = null

    private val viewModel: ItemListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            val adapter = ItemAdapter()
            binding.itemList.adapter = adapter
            viewModel.requestData(viewLifecycleOwner, binding.itemList, adapter)
        }
    }

    override fun onDestroyView() {
        // 页面销毁时记录列表滑动的位置
        val layoutManager = binding.itemList.layoutManager as LinearLayoutManager
        val findFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val firstVisibleView = layoutManager.findViewByPosition(findFirstVisibleItemPosition)
        val offset = firstVisibleView?.top ?: 0
        ITEM_LIST_FIRST_VISIBLE_ITEM_POSITION = findFirstVisibleItemPosition
        ITEM_LIST_OFFSET = offset

        job?.cancel()
        super.onDestroyView()
    }
}