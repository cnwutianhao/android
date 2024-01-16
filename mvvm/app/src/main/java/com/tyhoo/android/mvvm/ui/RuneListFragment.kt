package com.tyhoo.android.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tyhoo.android.mvvm.databinding.FragmentRuneListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RuneListFragment : Fragment() {

    private lateinit var binding: FragmentRuneListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRuneListBinding.inflate(inflater, container, false)
        return binding.root
    }
}