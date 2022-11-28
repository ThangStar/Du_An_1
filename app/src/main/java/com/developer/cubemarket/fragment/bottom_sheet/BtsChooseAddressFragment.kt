package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.adapter.bottom_sheet.BottomSheetChooseAddressAdapter
import com.developer.cubemarket.call_back_view.OnAddressSelected
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoDiaChi
import com.developer.cubemarket.connection.MODEL.OOP.Diachi
import com.developer.cubemarket.connection.callback.CallBackGetAddress
import com.developer.cubemarket.connection.callback.CallBackInsertAddress
import com.developer.cubemarket.databinding.FragmentBottomSheetChooseAddressBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

class BtsChooseAddressFragment(var onAddressSelected: OnAddressSelected) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "BottomSheetChooseAddress"
    }
    var arrAddress = arrayListOf<Diachi>()
    lateinit var binding: FragmentBottomSheetChooseAddressBinding
    lateinit var adapterAddress: BottomSheetChooseAddressAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetChooseAddressBinding.inflate(layoutInflater)
        initRecyclerAddress()
        initEventAddress()

        return binding.root

    }

    private fun initEventAddress() {
        binding.btnAddAddress.setOnClickListener {
            val address = binding.edtAddress.text.toString().trim()

            var isCheck = true
            if(Pattern.matches("^[\\S ]{1,80}\$", address)){
                binding.tilAddress.error = null
            }else{
                isCheck = false
                binding.tilAddress.error = "Địa chỉ không được để trống tối đa 80 kí tự"
            }
            //insert
            if (isCheck){

                /** 1. Insert current address
                 *  2. When insert success -> get data address
                 *  3. Set array old address = new address behind got return
                 */

                //1. Insert current address
                val callBackInsertAddress = object : CallBackInsertAddress{
                    override fun onSuccess(rs: String) {
                        Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()

                        //2. When insert success -> get data address
                        //3. Set array old address = new address behind got return
                        arrAddress = initDataAddress()
                    }

                    override fun onFail(err: String) {
                        Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(err: String) {
                        Toasty.error(requireContext(), err, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoDiaChi(requireContext()).insert_diachi(
                    callBackInsertAddress,
                    DataUser.id,
                    address
                )
                arrAddress.add(Diachi(0, DataUser.id, address))
            }
        }
    }

    private fun initRecyclerAddress() {
        adapterAddress = BottomSheetChooseAddressAdapter(this, initDataAddress(), onAddressSelected)
        binding.ryAddress.adapter = adapterAddress
    }

    private fun initDataAddress(): ArrayList<Diachi> {
        val callBackGet = object : CallBackGetAddress{
            override fun onSuccess(dc: Diachi) {
                arrAddress.add(dc)
                adapterAddress.notifyItemInserted(arrAddress.size)
            }

            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(rs: String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
        }
        DaoDiaChi(requireContext()).getdata_diachi(callBackGet, DataUser.id)
        return arrAddress
    }
}