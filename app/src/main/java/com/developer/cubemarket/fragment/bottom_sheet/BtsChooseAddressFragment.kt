package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.`object`.utils.AddressUser
import com.developer.cubemarket.adapter.bottom_sheet.BottomSheetChooseAddressAdapter
import com.developer.cubemarket.databinding.FragmentBottomSheetChooseAddressBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BtsChooseAddressFragment : BottomSheetDialogFragment() {
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
        val adapter = BottomSheetChooseAddressAdapter(initDataAddress())
        binding.ryAddress.adapter = adapter
    }

    private fun initDataAddress(): ArrayList<AddressUser> {
        var arr = arrayListOf<AddressUser>()
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        arr.add(AddressUser(0, "123 , Phường 456, Thành phố 789"))
        return arr
    }
}