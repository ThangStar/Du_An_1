package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BtsVoicherFragment : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "BTS_VOUCHER"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bts_voicher, container, false)
    }


}