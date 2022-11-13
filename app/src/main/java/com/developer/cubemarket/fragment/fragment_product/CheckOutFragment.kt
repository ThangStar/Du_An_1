package com.developer.cubemarket.fragment.fragment_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.checkout.ProductCheckOut
import com.developer.cubemarket.adapter.checkout.CheckOutCartAdapter
import com.developer.cubemarket.config.utils.utils
import com.developer.cubemarket.databinding.FragmentCheckOutBinding

class CheckOutFragment : Fragment() {

    lateinit var binding: FragmentCheckOutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckOutBinding.inflate(layoutInflater)

        initRecyclerProduct()
        return binding.root
    }

    private fun initRecyclerProduct() {
        val adapter = CheckOutCartAdapter(initDataProduct())
        binding.ryProduct.adapter = adapter
    }

    private fun initDataProduct(): ArrayList<ProductCheckOut> {
        val arr = arrayListOf<ProductCheckOut>()
        val bm1 = utils.resourceToBitmap(resources, R.drawable.product1)
        val bm2 = utils.resourceToBitmap(resources, R.drawable.product2)

        arr.add(ProductCheckOut(bm1, "Giày nike", "Size: M, Màu sắc: Trắng",
            290000, 1))
        arr.add(ProductCheckOut(bm2, "Mặt nạ hacker", "Size: M, Màu sắc: Trắng",
            190000, 2))
        arr.add(ProductCheckOut(bm1, "Giày nike", "Size: M, Màu sắc: Trắng",
            290000, 3))
        arr.add(ProductCheckOut(bm2, "Mặt nạ hacker", "Size: M, Màu sắc: Trắng",
            190000, 1))
        arr.add(ProductCheckOut(bm1, "Giày nike", "Size: M, Màu sắc: Trắng",
            290000, 2))
        arr.add(ProductCheckOut(bm2, "Mặt nạ hacker", "Size: M, Màu sắc: Trắng",
            190000, 1))
        arr.add(ProductCheckOut(bm1, "Giày nike", "Size: M, Màu sắc: Trắng",
            290000, 1))
        arr.add(ProductCheckOut(bm2, "Mặt nạ hacker", "Size: M, Màu sắc: Trắng",
            190000, 2))
        return arr
    }
}