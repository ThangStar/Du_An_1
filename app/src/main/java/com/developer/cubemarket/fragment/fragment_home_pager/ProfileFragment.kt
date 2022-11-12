package com.developer.cubemarket.fragment.fragment_home_pager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        initBtnPost()
        return binding.root
    }

    private fun initBtnPost() {
        binding.btnPost.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_postProductFragment)
        }
        binding.btnUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_updateProductFragment)
        }
    }

}