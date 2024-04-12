package com.tyhoo.android.mvvm.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tyhoo.android.mvvm.adapter.HeroAdapter
import com.tyhoo.android.mvvm.base.HERO_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.HERO_LIST_OFFSET
import com.tyhoo.android.mvvm.databinding.FragmentHeroListBinding
import com.tyhoo.android.mvvm.viewmodel.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private lateinit var binding: FragmentHeroListBinding

    private var job: Job? = null

    // 在 Fragment 中获取 ViewModel 的方式是通过宿主 Activity 的 by activityViewModels()
    private val viewModel: HeroListViewModel by activityViewModels()

    private lateinit var adapter: HeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        requestData()
        subscribeUI()
    }

    private fun initUI() {
        adapter = HeroAdapter()
        binding.heroList.adapter = adapter

        binding.fabMenu.setOnClickListener { viewModel.fabMenu() }
        binding.fabSearch.setOnClickListener { fabSearch(it) }
        binding.fabRevert.setOnClickListener { viewModel.heroRevert() }
        binding.fabTop.setOnClickListener { fabTop() }
    }

    private fun requestData() {
        job?.cancel()
        job = lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.requestData(viewLifecycleOwner)
            }
        }
    }

    private fun subscribeUI() {
        viewModel.heroList.observe(viewLifecycleOwner) { heroList ->
            adapter.submitList(heroList)
            val layoutManager = binding.heroList.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(
                HERO_LIST_FIRST_VISIBLE_ITEM_POSITION,
                HERO_LIST_OFFSET
            )
        }
    }

    private fun fabSearch(view: View) {
        val builder = AlertDialog.Builder(view.context)
        val input = EditText(view.context)
        input.hint = "请输入要搜索的英雄名"
        builder.setView(input)
        builder.setPositiveButton("确定") { dialog, _ ->
            val enteredText = input.text.toString()
            viewModel.heroSearch(enteredText)
            dialog.dismiss()
        }
        builder.setNegativeButton("取消") { dialog, _ ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()

        // 确保输入框获取焦点并打开软键盘
        val imm =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun fabTop() {
        val layoutManager = binding.heroList.layoutManager as LinearLayoutManager
        layoutManager.scrollToPosition(0)
    }

    override fun onDestroyView() {
        // 页面销毁时记录列表滑动的位置
        val layoutManager = binding.heroList.layoutManager as LinearLayoutManager
        val findFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val firstVisibleView = layoutManager.findViewByPosition(findFirstVisibleItemPosition)
        val offset = firstVisibleView?.top ?: 0
        HERO_LIST_FIRST_VISIBLE_ITEM_POSITION = findFirstVisibleItemPosition
        HERO_LIST_OFFSET = offset

        job?.cancel()
        super.onDestroyView()
    }
}