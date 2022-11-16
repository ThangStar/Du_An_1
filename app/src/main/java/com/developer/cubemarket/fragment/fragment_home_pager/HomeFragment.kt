package com.developer.cubemarket.fragment.fragment_home_pager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
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
import com.developer.cubemarket.`object`.ProductHome
import com.developer.cubemarket.adapter.DirectoryHomeAdapter
import com.developer.cubemarket.adapter.ProductHomeAdapter
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.databinding.FragmentHomeBinding
import com.developer.cubemarket.utils.CallBackProduct
import com.developer.cubemarket.utils.VolleyCallBack


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var ctx: Context

    companion object{
        lateinit var arrDirectory: ArrayList<Danhmuc>
        @SuppressLint("StaticFieldLeak")
        lateinit var adapterDirectory: DirectoryHomeAdapter
        lateinit var productHomeAdapter: ProductHomeAdapter
        var arrHomeProduct = arrayListOf<ProductHome>()
    }
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
        productHomeAdapter = ProductHomeAdapter(this, initDataProduct())
        binding.ryProduct.adapter = productHomeAdapter

    }

    private fun initDataProduct(): ArrayList<ProductHome> {
        val callBack = object : CallBackProduct {
            override fun DataProduct(pr: ProductHome) {
                arrHomeProduct.add(pr)
                productHomeAdapter.notifyItemInserted(arrHomeProduct.size)
            }
        }
        DaoSanPham(requireContext()).getdata_sanpham(callBack)

        return arrHomeProduct
    }

    private fun initRecyclerDireactory() {
        binding.ryDirectory.layoutManager = LinearLayoutManager(ctx, RecyclerView.HORIZONTAL, false)
        adapterDirectory = DirectoryHomeAdapter(requireContext(), this, initDataDirectory())
        binding.ryDirectory.adapter = adapterDirectory
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

    private fun initDataDirectory(): ArrayList<Danhmuc> {
        arrDirectory = arrayListOf()
        arrDirectory.clear()
        val data = VolleyCallBack(){
            arrDirectory.add(it)
            Log.d("SSS", it.tendanhmuc)
            adapterDirectory.notifyItemInserted(arrDirectory.size)
        }
        DaoDanhMuc(requireContext()).getdata_danhmuc(data)

        return arrDirectory
    }

}