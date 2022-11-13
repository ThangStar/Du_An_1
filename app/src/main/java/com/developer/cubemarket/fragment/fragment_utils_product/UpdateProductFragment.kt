package com.developer.cubemarket.fragment.fragment_utils_product

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.utils.color.ColorAdapter
import com.developer.cubemarket.adapter.utils.size.SizeAdapter
import com.developer.cubemarket.databinding.FragmentProductBinding
import com.developer.cubemarket.databinding.FragmentUpdateProductBinding

    class UpdateProductFragment : Fragment() {
    lateinit var binding: FragmentUpdateProductBinding
    @SuppressLint("StaticFieldLeak")
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var sizeAdapter: SizeAdapter
        @SuppressLint("StaticFieldLeak")
        lateinit var colorAdapter: ColorAdapter

        val arrSize = arrayListOf<String>()
        val arrColor = arrayListOf<String>()
    }


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
            colorAdapter = ColorAdapter(requireActivity(), initColor())
            binding.ryColor.adapter = colorAdapter
        }

        private fun initColor(): ArrayList<String> {
            arrColor.add("Xanh")
            arrColor.add("Đỏ")
            arrColor.add("Vàng")
            return arrColor
        }

        private fun initRecyclerSize() {
            sizeAdapter = SizeAdapter(requireActivity(), initSize())
            binding.rySize.adapter = sizeAdapter
        }

        private fun initSize(): ArrayList<String> {
            arrSize.add("M")
            arrSize.add("L")
            arrSize.add("X")
            arrSize.add("XL")
            arrSize.add("XXL")
            return arrSize
        }
    }