package com.developer.cubemarket.fragment.fragment_home_pager

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.DirectoryHome
import com.developer.cubemarket.`object`.ProductHome
import com.developer.cubemarket.adapter.DirectoryHomeAdapter
import com.developer.cubemarket.adapter.ProductHomeAdapter
import com.developer.cubemarket.config.utils.utils
import com.developer.cubemarket.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        ctx = binding.root.context
        postponeEnterTransition()
        initRecyclerDireactory()
        initRecyclerProduct()
        binding.ryProduct.doOnPreDraw {
            startPostponedEnterTransition()
        }

        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(
            R.transition.shared_image)
        exitTransition = TransitionInflater.from(ctx).inflateTransition(
            R.transition.shared_image)
        return binding.root
    }

    private fun initRecyclerProduct() {
        val customLayout = object : GridLayoutManager(ctx, 2){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.ryProduct.layoutManager = customLayout
        val adapter = ProductHomeAdapter(this, initDataProduct())
        binding.ryProduct.adapter = adapter

    }

    private fun initDataProduct(): ArrayList<ProductHome> {
        var arr = arrayListOf<ProductHome>()
        val bm = utils.resourceToBitmap(resources, R.drawable.product1)
        arr.add(ProductHome(bm, nameProduct = "PRODUCT1", priceProduct = 123131))
        arr.add(ProductHome(bm, nameProduct = "PRODUCT1", priceProduct = 123131))
        arr.add(ProductHome(bm, nameProduct = "PRODUCT1", priceProduct = 123131))
        arr.add(ProductHome(bm, nameProduct = "PRODUCT1", priceProduct = 123131))
        arr.add(ProductHome(bm, nameProduct = "PRODUCT1", priceProduct = 123131))
        return arr
    }

    private fun initRecyclerDireactory() {
        binding.ryDirectory.layoutManager = LinearLayoutManager(ctx, RecyclerView.HORIZONTAL, false)
        val adapter = DirectoryHomeAdapter(initDataDirectory())
        binding.ryDirectory.adapter = adapter
        binding.ryDirectory.addOnItemTouchListener(object: OnItemTouchListener{
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