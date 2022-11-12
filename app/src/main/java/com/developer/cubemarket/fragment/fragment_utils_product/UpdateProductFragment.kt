package com.developer.cubemarket.fragment.fragment_utils_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.utils.size.SizeAdapter
import com.developer.cubemarket.databinding.FragmentProductBinding
import com.developer.cubemarket.databinding.FragmentUpdateProductBinding

    class UpdateProductFragment : Fragment() {
    lateinit var binding: FragmentUpdateProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateProductBinding.inflate(layoutInflater)
        //main code

        initRecyclerSize()
        initRecycelrColor()
        //end code
        return binding.root
    }

        private fun initRecycelrColor() {
            val adapter = SizeAdapter(initColor())
            binding.ryColor.adapter = adapter
        }

        private fun initColor(): ArrayList<String> {
            val arr = arrayListOf<String>()
            arr.add("Xanh")
            arr.add("Đỏ")
            arr.add("Vàng")
            return arr
        }

        private fun initRecyclerSize() {
            val adapter = SizeAdapter(initSize())
            binding.rySize.adapter = adapter
        }

        private fun initSize(): ArrayList<String> {
            val arr = arrayListOf<String>()
            arr.add("M")
            arr.add("L")
            arr.add("X")
            arr.add("XL")
            arr.add("XXL")
            return arr
        }
    }