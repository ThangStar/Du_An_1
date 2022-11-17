package com.developer.cubemarket.fragment.fragment_home_pager

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.cubemarket.`object`.search.historysearch
import com.developer.cubemarket.`object`.search.ketquaObject
import com.developer.cubemarket.`object`.search.recently
import com.developer.cubemarket.adapter.search.historyAdapter
import com.developer.cubemarket.adapter.search.ketquaAdapter
import com.developer.cubemarket.adapter.search.recentlyAdapter
import com.developer.cubemarket.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        ctx = binding.root.context

        //gần đây
        getListRecently();

        //lịch sử tìm kiếm
        getListHistory();
        //kết quả tìm kiếm
        getListResult();

        //camera

        //clear

        //
        return binding.root
    }
    private fun getListResult(): List<ketquaObject> {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.resultRecyclerView.layoutManager = linearLayoutManager
        val list: MutableList<ketquaObject> = ArrayList()
        list.add(ketquaObject(R.drawable.ic_menu_report_image, "Giày nike", "đ 690.000"))
        list.add(ketquaObject(R.drawable.checkbox_on_background, "Son Môi Micracle", "đ 250.00"))
        val ketquaAdapter = ketquaAdapter(list)
        binding.resultRecyclerView.adapter = ketquaAdapter
        return list
    }

    private fun getListHistory(): List<historysearch> {
    val linearLayoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.searchHistoryRecycleView.layoutManager = linearLayoutManager1
        val list: MutableList<historysearch> = ArrayList()
        list.add(historysearch("M.O.I"))
        list.add(historysearch("Mirac"))
        list.add(historysearch("Son"))
        list.add(historysearch("Flawsome"))
        list.add(historysearch("Glowy"))
        list.add(historysearch("Lip"))
        list.add(historysearch("Apo"))
        val historyAdapter = historyAdapter(list)
        binding.searchHistoryRecycleView.adapter = historyAdapter
        return list
    }

    private fun getListRecently(): List<recently> {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recentlyRecycleView.setLayoutManager(linearLayoutManager)
        val list: MutableList<recently> = ArrayList()
        list.add(recently(R.drawable.ic_menu_report_image, "Son lì Shu Uem", "đ 360.00"))
        list.add(recently(R.drawable.ic_menu_report_image, "Son Môi Micracle", "đ 250.00"))
        val recentlyAdapter = recentlyAdapter(list)
        binding.recentlyRecycleView.adapter = recentlyAdapter
        return list
    }
}