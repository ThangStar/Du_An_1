package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.adapter.voucher.VoucherUserAdapter
import com.developer.cubemarket.call_back_view.OnVoucherSelected
import com.developer.cubemarket.connection.MODEL.DAO.DaoKhuyenMai
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai
import com.developer.cubemarket.connection.callback.CallBackVoicher
import com.developer.cubemarket.databinding.FragmentBtsVoicherBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.dmoral.toasty.Toasty

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BtsVoicherFragment(var sumMoney: Int, var onVoucherSelected: OnVoucherSelected) : BottomSheetDialogFragment() {
    lateinit var adapterVoucher: VoucherUserAdapter
    private var arrVoucher = arrayListOf<KhuyenMai>()
    lateinit var binding: FragmentBtsVoicherBinding
    companion object {
        const val TAG = "BTS_VOUCHER"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBtsVoicherBinding.inflate(layoutInflater)

        initRecyclerVoucher()
        initEventSubmitVoucher()



        return binding.root
    }

    private fun initEventSubmitVoucher() {
        binding.btnAccept.setOnClickListener {
            val code = binding.edtVoucher.text.toString().trim()
            var isCheck = true
            if(code.isBlank()){
                isCheck = false
                binding.tilVoucher.error = "Vui lòng nhập mã giảm giá"
            }else{
                binding.tilVoucher.error = null
            }

            if(isCheck){
                arrVoucher.clear()
                val callBackVoicher = object : CallBackVoicher{
                    override fun onSuccess(km: KhuyenMai) {
                        arrVoucher.add(km)
                        adapterVoucher.notifyItemInserted(arrVoucher.size)
                    }

                    override fun onFail(rs: String) {
                        binding.tilVoucher.error = rs
                    }

                    override fun onError(rs: String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoKhuyenMai(requireContext()).nhapma_giamgia(callBackVoicher, code)
            }
        }
    }

    private fun initRecyclerVoucher() {
        adapterVoucher = VoucherUserAdapter(this, sumMoney, initDataVoucher(), onVoucherSelected)
        binding.ryVoucher.adapter = adapterVoucher
    }

    private fun initDataVoucher(): ArrayList<KhuyenMai> {
        return arrVoucher
    }


}