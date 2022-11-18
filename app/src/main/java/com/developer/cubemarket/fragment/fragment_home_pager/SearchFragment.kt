package com.developer.cubemarket.fragment.fragment_home_pager

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.search.historysearch
import com.developer.cubemarket.`object`.search.recently
import com.developer.cubemarket.adapter.search.historyAdapter
import com.developer.cubemarket.adapter.search.ketquaAdapter
import com.developer.cubemarket.adapter.search.recentlyAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.databinding.FragmentSearchBinding
import com.developer.cubemarket.utils.CallBackSearchProduct
import com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var ketquaAdapter: ketquaAdapter
    lateinit var ctx: Context
    val listRsSearch: MutableList<Sanpham> = ArrayList()


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

        initEventSearch()


        //camera

        //clear

        //
        return binding.root
    }

    private fun initEventSearch() {


        binding.search.addTextChangeListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val arrNameProduct = arrayListOf<String>()

                val callBack = object: CallBackSearchProduct{
                    override fun onSuccess(sp: Sanpham) {
                        arrNameProduct.add(sp.tensanpham)
                    }
                    override fun onFail(rs: String) {
                    }
                    override fun onError(rs: String) {
                    }
                    override fun onFinish() {
                        binding.search.lastSuggestions = arrNameProduct
                    }

                }


                binding.search.clearSuggestions()
                DaoSanPham(requireContext()).search_sanpham_chung(callBack, p0.toString(),
                    DataUser.id, DataUser.occupation)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        val callBackSearch = object: CallBackSearchProduct{
            override fun onSuccess(sp: Sanpham) {
                if (listRsSearch.size < 6){
                    listRsSearch.add(sp)
                }
                ketquaAdapter.notifyItemInserted(listRsSearch.size)
            }

            override fun onFail(rs: String) {
            }

            override fun onError(rs: String) {
            }

            override fun onFinish() {
            }

        }
        binding.search.setOnSearchActionListener(object: OnSearchActionListener{
            override fun onSearchStateChanged(enabled: Boolean) {
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                listRsSearch.clear()
                ketquaAdapter.notifyDataSetChanged()
                binding.search.clearSuggestions()
                binding.search.clearFocus()
                //set text keyword search
                binding.tvResult.text = "Kết quả từ khóa: '$text'"
                DaoSanPham(requireContext()).search_sanpham_chung(callBackSearch, text.toString(),
                    DataUser.id, DataUser.occupation)
            }

            override fun onButtonClicked(buttonCode: Int) {
            }
        })
    }

    private fun getListResult(): List<Sanpham> {
        ketquaAdapter = ketquaAdapter(requireContext(), listRsSearch)
        binding.resultRecyclerView.adapter = ketquaAdapter
        return listRsSearch
    }

    private fun getListHistory(): List<historysearch> {
    val linearLayoutManager1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.ryHis2.layoutManager = linearLayoutManager1
        val list: MutableList<historysearch> = ArrayList()
        list.add(historysearch("M.O.I"))
        list.add(historysearch("Mirac"))
        list.add(historysearch("Son"))
        list.add(historysearch("Flawsome"))
        list.add(historysearch("Glowy"))
        list.add(historysearch("Lip"))
        list.add(historysearch("Apo"))
        val historyAdapter = historyAdapter(list)
        binding.ryHis2.adapter = historyAdapter
        return list
    }

    private fun getListRecently(): List<recently> {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.ryHis.layoutManager = linearLayoutManager
        val list: MutableList<recently> = ArrayList()
        list.add(recently(R.drawable.product2, "Son lì Shu Uem", "đ 360.00"))
        list.add(recently(R.drawable.product2, "Son Môi Micracle", "đ 250.00"))
        val recentlyAdapter = recentlyAdapter(list)
        binding.ryHis.adapter = recentlyAdapter
        return list
    }
}