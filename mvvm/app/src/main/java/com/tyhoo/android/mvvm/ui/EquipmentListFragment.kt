package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tyhoo.android.mvvm.adapter.EquipmentAdapter
import com.tyhoo.android.mvvm.base.EQUIPMENT_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.EQUIPMENT_LIST_OFFSET
import com.tyhoo.android.mvvm.databinding.FragmentEquipmentListBinding
import com.tyhoo.android.mvvm.viewmodel.EquipmentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class EquipmentListFragment : Fragment() {

    private lateinit var binding: FragmentEquipmentListBinding

    private var job: Job? = null

    private val viewModel: EquipmentListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEquipmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launchWhenResumed {
            val adapter = EquipmentAdapter()
            binding.equipmentList.adapter = adapter
            viewModel.requestData(viewLifecycleOwner, binding.equipmentList, adapter)
        }
    }

    override fun onDestroyView() {
        // 页面销毁时记录列表滑动的位置
        val layoutManager = binding.equipmentList.layoutManager as LinearLayoutManager
        val findFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val firstVisibleView = layoutManager.findViewByPosition(findFirstVisibleItemPosition)
        val offset = firstVisibleView?.top ?: 0
        EQUIPMENT_LIST_FIRST_VISIBLE_ITEM_POSITION = findFirstVisibleItemPosition
        EQUIPMENT_LIST_OFFSET = offset

        job?.cancel()
        super.onDestroyView()
    }
}