package com.developer.cubemarket.fragment.voicher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.voucher.VoucherManagerAdapter
import com.developer.cubemarket.connection.MODEL.DAO.DaoKhuyenMai
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai
import com.developer.cubemarket.connection.callback.CallBackVoicher
import com.developer.cubemarket.databinding.FragmentVoicherManagerBinding
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class VoicherManagerFragment : Fragment() {
    lateinit var adapterVoicher: VoucherManagerAdapter
    lateinit var binding: FragmentVoicherManagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentVoicherManagerBinding.inflate(layoutInflater)

        initEventBackPresent()
        initTimeCurrent()
        initRecyclerVoicher()
        initGoAddVoicher()
        return binding.root
    }

    private fun initEventBackPresent() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initTimeCurrent() {
        val c: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val strDate: String = sdf.format(c.getTime())
        binding.tvSubTitle.text = "Ngày hiện tại: $strDate"
    }

    private fun initGoAddVoicher() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_voicherManagerFragment_to_insertVoicherFragment)
        }
    }

    private fun initRecyclerVoicher() {
        adapterVoicher = VoucherManagerAdapter(this, initDataVoidcher())
        binding.ryVoicher.adapter = adapterVoicher
    }

    private fun initDataVoidcher(): ArrayList<KhuyenMai> {
        val arr = arrayListOf<KhuyenMai>()
        val callBackVoicher = object : CallBackVoicher{
            override fun onSuccess(km: KhuyenMai) {
                arr.add(km)
                adapterVoicher.notifyItemInserted(arr.size)
            }

            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(rs: String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

        }
        DaoKhuyenMai(requireContext()).getdata_all(callBackVoicher)
        return arr
    }
}