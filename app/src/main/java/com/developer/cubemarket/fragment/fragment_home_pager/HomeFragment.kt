package com.developer.cubemarket.fragment.fragment_home_pager

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.DirectoryHomeAdapter
import com.developer.cubemarket.adapter.ProductHomeAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.connection.callback.CallBackProduct
import com.developer.cubemarket.databinding.FragmentHomeBinding
import com.developer.cubemarket.fragment.ProductFragment
import com.developer.cubemarket.connection.callback.VolleyCallBack
import com.google.android.material.transition.MaterialContainerTransform
import com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener
import es.dmoral.toasty.Toasty


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var ctx: Context
    private var arrAutoComplete = arrayListOf<String>()

    private var adapterCompleteSearch: ArrayAdapter<String>? = null
    companion object{
        lateinit var arrDirectory: ArrayList<Danhmuc>
        lateinit var adapterDirectory: DirectoryHomeAdapter
        lateinit var productHomeAdapter: ProductHomeAdapter
        var arrHomeProduct = arrayListOf<Sanpham>()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        ctx = binding.root.context



        initCompleteSearch()

        initIsShowRyDirectory()
        postponeEnterTransition()
        initRecyclerDireactory()
        initRecyclerProduct()
        initSearch()



        return binding.root
    }

    private fun initIsShowRyDirectory() {
        var isShow = false
        binding.tvIsShow.setOnClickListener {
            isShow = !isShow
            if(isShow){
                binding.ryDirectory.visibility = View.GONE
                binding.tvIsShow.text = "HIỆN"
            }else{
                binding.ryDirectory.visibility = View.VISIBLE
                binding.tvIsShow.text = "ẨN"
            }
        }
    }

    private fun initCompleteSearch() {
        adapterCompleteSearch =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrAutoComplete)

        binding.sbrHome.lastSuggestions = arrAutoComplete

    }

    private fun initSearch() {
        binding.sbrHome.addTextChangeListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //text changed
                val arr = arrayListOf<String>()
                for (i in arrAutoComplete){
                    if(arr.size < 6){
                        if(i.contains(p0.toString())){
                            arr.add(i)
                        }
                    }
                }
                binding.sbrHome.lastSuggestions = arr

            }

            override fun afterTextChanged(p0: Editable?) {
            //
            }
        })
        binding.sbrHome.setOnSearchActionListener(object: OnSearchActionListener{
            override fun onSearchStateChanged(enabled: Boolean) {
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                ProductFragment.binding.pagerProduct.setCurrentItem(1, true)
            }

            override fun onButtonClicked(buttonCode: Int) {
            }
        })
    }

    private fun initRecyclerProduct() {
        val customLayout = object : GridLayoutManager(ctx, 2){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.ryProduct.layoutManager = customLayout
        productHomeAdapter = ProductHomeAdapter(this, initDataProduct())
        binding.ryProduct.adapter = productHomeAdapter

    }

    private fun initDataProduct(): ArrayList<Sanpham> {
        arrHomeProduct.clear()
        arrAutoComplete.clear()

        val callBack = object : CallBackProduct {
            override fun onSuccess(sp: Sanpham) {
                arrHomeProduct.add(sp)
                productHomeAdapter.notifyItemInserted(arrHomeProduct.size)
                arrAutoComplete.add(sp.tensanpham)
            }

            override fun onFail(err: String) {
                Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()

            }

            override fun onError(err: String) {
                Toasty.error(requireContext(), err, Toasty.LENGTH_SHORT).show()
            }


        }
        DaoSanPham(requireContext()).getdata_sanpham(callBack, DataUser.id, DataUser.occupation)
        return arrHomeProduct
    }

    private fun initRecyclerDireactory() {
        binding.ryDirectory.layoutManager = LinearLayoutManager(ctx, RecyclerView.HORIZONTAL, false)
        adapterDirectory = DirectoryHomeAdapter(requireContext(), this, initDataDirectory())
        binding.ryDirectory.adapter = adapterDirectory
        binding.ryDirectory.addOnItemTouchListener(object: OnItemTouchListener{
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action: Int = e.getAction()
                when (action) {
                    MotionEvent.ACTION_DOWN -> rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

        })
    }

    private fun initDataDirectory(): ArrayList<Danhmuc> {
        arrDirectory = arrayListOf()
        arrDirectory.clear()
        val data = VolleyCallBack() {
                arrDirectory.add(it)
                Log.d("SSS", it.tendanhmuc)
                adapterDirectory.notifyItemInserted(arrDirectory.size)
            }
        DaoDanhMuc(requireContext()).getdata_danhmuc(data)

        return arrDirectory
    }

}