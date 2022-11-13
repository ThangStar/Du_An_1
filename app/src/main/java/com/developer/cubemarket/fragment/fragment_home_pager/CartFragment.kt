package com.developer.cubemarket.fragment.fragment_home_pager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.DirectoryHome
import com.developer.cubemarket.`object`.cart.CartProduct
import com.developer.cubemarket.adapter.DirectoryHomeAdapter
import com.developer.cubemarket.adapter.cart.CartProductAdapter
import com.developer.cubemarket.config.utils.utils
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
        initRecyclerDireactory()
        initRecyclerProduct()
        initGoCheckOut()

        return binding.root
    }

    private fun initGoCheckOut() {
        binding.lnGoCheckOut.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_checkOutFragment)
        }
    }

    private fun initRecyclerProduct() {
        val adapter = CartProductAdapter(initDataCartProduct())
        binding.ryProduct.adapter = adapter
    }

    private fun initDataCartProduct():  ArrayList<CartProduct> {
        var arr = arrayListOf<CartProduct>()
        val bm = utils.resourceToBitmap(resources, R.drawable.product1)
        val bm2 = utils.resourceToBitmap(resources, R.drawable.product2)
        arr.add(CartProduct(bm, "Giày nike", "size: 41", 690000))
        arr.add(CartProduct(bm2, "Mặt nạ hacker", "size: 41", 690300))
        arr.add(CartProduct(bm, "Giày nike", "size: 41", 534000))
        arr.add(CartProduct(bm2, "Mặt nạ hacker", "size: 41", 6234000))
        arr.add(CartProduct(bm, "Giày nike", "size: 41", 6490000))
        return arr
    }

    private fun initRecyclerDireactory() {
        binding.ryDirectory.layoutManager = LinearLayoutManager(ctx, RecyclerView.HORIZONTAL, false)
        val adapter = DirectoryHomeAdapter(initDataDirectory())
        binding.ryDirectory.adapter = adapter
        binding.ryDirectory.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action: Int = e.getAction()
                when (action) {
                    MotionEvent.ACTION_DOWN -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

        })
    }
    private fun initDataDirectory(): ArrayList<DirectoryHome> {
        var arr = arrayListOf<DirectoryHome>()
        arr.add(DirectoryHome(R.drawable.top, "Hàng đầu"))
        arr.add(DirectoryHome(R.drawable.trend, "Xu hướng"))
        arr.add(DirectoryHome(R.drawable.pic_new, "Mới"))
        arr.add(DirectoryHome(R.drawable.fast, "Bán chạy"))
        arr.add(DirectoryHome(R.drawable.other, "Khác"))

        return arr
    }


}