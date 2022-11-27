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
import com.airbnb.lottie.utils.Utils
import com.bumptech.glide.util.Util
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.DirectoryHomeAdapter
import com.developer.cubemarket.adapter.cart.CartProductAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.connection.callback.CallBackSelectCart
import com.developer.cubemarket.databinding.FragmentCartBinding
import es.dmoral.toasty.Toasty

class CartFragment : Fragment() {
    val arr = arrayListOf<GioHang>()
    lateinit var binding: FragmentCartBinding
    lateinit var adapterProduct: CartProductAdapter
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater)
        ctx = binding.root.context

        initRecyclerProduct()

        //Default

        initRecyclerDireactory()
        initGoCheckOut()

        return binding.root
    }

    private fun initGoCheckOut() {
        binding.lnGoCheckOut.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_checkOutFragment)
        }
    }

    private fun initRecyclerProduct() {
        adapterProduct = CartProductAdapter(this, initDataCartProduct())
        binding.ryProduct.adapter = adapterProduct
    }

    private fun initDataCartProduct():  ArrayList<GioHang> {
        arr.clear()
        var uiPrice = 0
        var uiCount = 0
        val callBackSelect = object : CallBackSelectCart{
            override fun onSuccess(gh: GioHang) {
                arr.add(gh)
                //Update ui for price
                uiPrice += gh.gia
                ++uiCount
                binding.tvPrice.text = com.developer.cubemarket.config.utils.Utils.formaterVND(uiPrice)
                binding.tvCount.text = "$uiCount"

                //update ui array cart
                adapterProduct.notifyItemInserted(arr.size)
            }
            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
            override fun onError(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
        }
        DaoGioHang(requireContext()).getdata_giohang(callBackSelect, DataUser.id)
        return arr
    }

    private fun initRecyclerDireactory() {
        binding.ryDirectory.layoutManager = LinearLayoutManager(ctx, RecyclerView.HORIZONTAL, false)
        val adapter = DirectoryHomeAdapter(requireContext(), this, initDataDirectory())
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
    private fun initDataDirectory(): ArrayList<Danhmuc> {
        var arr = arrayListOf<Danhmuc>()

        return arr
    }


}