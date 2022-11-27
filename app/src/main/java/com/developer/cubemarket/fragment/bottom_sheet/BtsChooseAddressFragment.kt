package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.`object`.utils.AddressUser
import com.developer.cubemarket.adapter.bottom_sheet.BottomSheetChooseAddressAdapter
import com.developer.cubemarket.call_back_view.OnAddressSelected
import com.developer.cubemarket.connection.MODEL.OOP.Diachi
import com.developer.cubemarket.databinding.FragmentBottomSheetChooseAddressBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BtsChooseAddressFragment(var onAddressSelected: OnAddressSelected) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "BottomSheetChooseAddress"
    }
    lateinit var binding: FragmentBottomSheetChooseAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetChooseAddressBinding.inflate(layoutInflater)
        initRecyclerAddress()
        return binding.root

    }

    private fun initRecyclerAddress() {
        val adapter = BottomSheetChooseAddressAdapter(this, initDataAddress(), onAddressSelected)
        binding.ryAddress.adapter = adapter
    }

    private fun initDataAddress(): ArrayList<Diachi> {
        var arr = arrayListOf<Diachi>()
        arr.add(Diachi(2,2,"123 , Phường 456, Thành phố 789"))
        arr.add(Diachi(2,2,"fwefw , Phường 23fwe, Thành phố fwef"))
        arr.add(Diachi(2,2,"333333 , Phường 4dsvsd56, Thành phố gerg"))
        arr.add(Diachi(2,2,"grdsv , Phường fsev, Thành phố gersd"))
        arr.add(Diachi(2,2,"gdrdb , Phường 45vewsv6, Thành phố wqfwe"))

        return arr
    }
}