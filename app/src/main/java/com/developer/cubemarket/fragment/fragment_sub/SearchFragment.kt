package com.developer.cubemarket.fragment.fragment_sub

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentProfileBinding
import com.developer.cubemarket.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        ctx = binding.root.context
        return binding.root
    }
}