package com.developer.cubemarket.fragment.fragment_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.adapter.checkout.CheckOutCartAdapter
import com.developer.cubemarket.call_back_view.OnAddressSelected
import com.developer.cubemarket.call_back_view.OnAmountChange
import com.developer.cubemarket.call_back_view.OnVoucherSelected
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.DAO.DaoHoaDon
import com.developer.cubemarket.connection.MODEL.OOP.Diachi
import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai
import com.developer.cubemarket.connection.callback.CallBackInsertBill
import com.developer.cubemarket.connection.callback.CallBackSelectCart
import com.developer.cubemarket.databinding.FragmentCheckOutBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsChooseAddressFragment
import com.developer.cubemarket.fragment.bottom_sheet.BtsVoicherFragment
import es.dmoral.toasty.Toasty

class CheckOutFragment : Fragment() {
    var sumMoney = 0
    var finalSumMoney = 0
    var voucherPercentMoney = 0

    var codeVoucher = ""

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
        initGoSelectVoucher()
        initEventOrder()


        return binding.root
    }

    private fun initGoSelectVoucher() {
        binding.rlGoSelectVoucher.setOnClickListener {
            val onVoucherSelected = object : OnVoucherSelected {
                override fun onSelected(km: KhuyenMai) {
                    codeVoucher = km.magiamgia
                    voucherPercentMoney = km.phantramgiam
                    binding.tvSumVoicher.text = "- đ ${km.phantramgiam}%"
                    finalSumMoney = sumMoney - sumMoney * voucherPercentMoney / 100
                    binding.tvPriceFinal.text = Utils.formaterVND(finalSumMoney)
                }
            }

            val model = BtsVoicherFragment(sumMoney, onVoucherSelected)
            model.show(requireActivity().supportFragmentManager, BtsVoicherFragment.TAG)
        }
    }

    private fun initEventOrder() {
        binding.btnOrder.setOnClickListener {
            val callBackInsertBill = object : CallBackInsertBill{
                override fun onSuccess(rs: String) {
                    Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

                override fun onFail(err: String) {
                    Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(error: String) {
                    Toasty.error(requireContext(), error, Toasty.LENGTH_SHORT).show()
                }
            }
            Toasty.success(requireContext(), codeVoucher, Toasty.LENGTH_SHORT).show()
            DaoHoaDon(requireContext()).insert_hoadon(callBackInsertBill, DataUser.id, binding.tvAddress.text.toString(), codeVoucher)
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
        val onAmountChange = object : OnAmountChange {
            override fun onChange(priceSum: Int) {
                sumMoney = priceSum
                binding.tvSumPrice.text = Utils.formaterVND(sumMoney)
                finalSumMoney = priceSum+20000
                finalSumMoney = sumMoney - sumMoney * voucherPercentMoney / 100
                binding.tvPriceFinal.text = Utils.formaterVND(finalSumMoney)
            }

        }
        adapterProduct = CheckOutCartAdapter(this, onAmountChange, initDataProduct())
        binding.ryProduct.adapter = adapterProduct
    }

    private fun initDataProduct(): ArrayList<GioHang> {
        val arr = arrayListOf<GioHang>()
        val callBackSelect = object : CallBackSelectCart {
            override fun onSuccess(gh: GioHang) {

                sumMoney += gh.gia*gh.soluong

                arr.add(gh)
                adapterProduct.notifyItemInserted(arr.size)
            }
            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
            override fun onError(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
            override fun onFinish() {
                binding.tvSumPrice.text = Utils.formaterVND(sumMoney)
                finalSumMoney = sumMoney+20000
                binding.tvPriceFinal.text = Utils.formaterVND(finalSumMoney)
            }
        }
        DaoGioHang(requireContext()).getdata_giohang(callBackSelect, DataUser.id)
        return arr
    }
}