package com.developer.cubemarket.fragment.fragment_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.checkout.ProductCheckOut
import com.developer.cubemarket.adapter.cart.CartProductAdapter
import com.developer.cubemarket.adapter.checkout.CheckOutCartAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.DAO.DaoHoaDon
import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.connection.callback.CallBackSelectCart
import com.developer.cubemarket.databinding.FragmentCheckOutBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsChooseAddressFragment
import es.dmoral.toasty.Toasty

class CheckOutFragment : Fragment() {

    lateinit var binding: FragmentCheckOutBinding
    lateinit var adapterProduct: CheckOutCartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCheckOutBinding.inflate(layoutInflater)
        initRecyclerProduct()
        initEditAddress()
        initEventOrder()


        return binding.root
    }

    private fun initEventOrder() {
        binding.btnOrder.setOnClickListener {
            DaoHoaDon(requireContext()).insert_hoadon(DataUser.id, "123 abc 456 def", "ms1")
        }
    }

    private fun initEditAddress() {
        binding.imvShowBottomSheet.setOnClickListener {
            val model = BtsChooseAddressFragment()
            model.show(requireActivity().supportFragmentManager, BtsChooseAddressFragment.TAG)

        }
    }


    private fun initRecyclerProduct() {
        adapterProduct = CheckOutCartAdapter(this, initDataProduct())
        binding.ryProduct.adapter = adapterProduct
    }

    private fun initDataProduct(): ArrayList<GioHang> {
        val arr = arrayListOf<GioHang>()
        val callBackSelect = object : CallBackSelectCart {
            override fun onSuccess(gh: GioHang) {
                arr.add(gh)
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
}