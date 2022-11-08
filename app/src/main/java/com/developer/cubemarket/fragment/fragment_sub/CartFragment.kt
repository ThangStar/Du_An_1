package com.developer.cubemarket.fragment.fragment_sub

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater)
        ctx = binding.root.context

        //Do something here


        return binding.root
    }
}