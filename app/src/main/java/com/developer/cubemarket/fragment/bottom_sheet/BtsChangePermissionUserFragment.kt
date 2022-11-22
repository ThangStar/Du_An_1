package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentBottomSheetChooseAddressBinding
import com.developer.cubemarket.databinding.FragmentBtsChangePermissionUserBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BtsChangePermissionUserFragment : BottomSheetDialogFragment() {
    lateinit var binding : FragmentBtsChangePermissionUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentBtsChangePermissionUserBinding.inflate(layoutInflater)
        initDataPermission()
        return binding.root
    }

    private fun initDataPermission() {
        val items = listOf("Người dùng", "Người bán", "Admin")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_permission_item, items)
        (binding.atcPermission as? AutoCompleteTextView)?.setAdapter(adapter)
        binding.atcPermission.setText(items[0], false);
    }

    companion object {
        const val TAG = "BtsChangePermissionUser"
    }
}