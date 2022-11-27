package com.developer.cubemarket.fragment.fragment_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.adapter.checkout.CheckOutCartAdapter
import com.developer.cubemarket.call_back_view.OnAddressSelected
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.DAO.DaoHoaDon
import com.developer.cubemarket.connection.MODEL.OOP.Diachi
import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.connection.callback.CallBackSelectCart
import com.developer.cubemarket.databinding.FragmentCheckOutBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsChooseAddressFragment
import com.developer.cubemarket.fragment.bottom_sheet.BtsVoicherFragment
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
        initGoSelectVoucher()


        return binding.root
    }

    private fun initGoSelectVoucher() {
        binding.rlGoSelectVoucher.setOnClickListener {
            val model = BtsVoicherFragment()
            model.show(requireActivity().supportFragmentManager, BtsVoicherFragment.TAG)
        }
    }

    private fun initEventOrder() {
        binding.btnOrder.setOnClickListener {
            DaoHoaDon(requireContext()).insert_hoadon(DataUser.id, binding.tvAddress.text.toString(), "ms1")
        }
    }

    private fun initEditAddress() {
        binding.imvShowBottomSheet.setOnClickListener {

            val onAddressSelected = object : OnAddressSelected {
                override fun onSelected(dc: Diachi) {
                    binding.tvAddress.text = dc.tendiachi
                }
            }
            val model = BtsChooseAddressFragment(onAddressSelected)
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