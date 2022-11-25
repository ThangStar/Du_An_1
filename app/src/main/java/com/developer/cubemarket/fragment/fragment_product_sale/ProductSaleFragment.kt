package com.developer.cubemarket.fragment.fragment_product_sale

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developer.cubemarket.adapter.sale.SaleProductAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.connection.callback.CallBackProductSale
import com.developer.cubemarket.databinding.FragmentProductSaleBinding
import es.dmoral.toasty.Toasty

class ProductSaleFragment : Fragment() {
    lateinit var binding: FragmentProductSaleBinding
    lateinit var adapterProduct: SaleProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductSaleBinding.inflate(layoutInflater)
        initRecyclerProduct()
        return binding.root
    }

    private fun initRecyclerProduct() {
        adapterProduct = SaleProductAdapter(requireContext(), initDataCartProduct())
        binding.ryProduct.adapter = adapterProduct
    }
    private fun initDataCartProduct():  ArrayList<Sanpham> {
        var arr = arrayListOf<Sanpham>()
        val callBack = object: CallBackProductSale {
            override fun onSuccess(sp: Sanpham) {
                arr.add(sp)
                adapterProduct.notifyItemInserted(arr.size)
                Log.d("AAA", "in list")

            }

            override fun onFail(err: String) {
                Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(err: String) {
                Toasty.error(requireContext(), err, Toasty.LENGTH_SHORT).show()
            }

        }
        Log.d("AAA", "id: ${DataUser.id}, chucvu: ${DataUser.occupation}")
        DaoSanPham(requireContext()).getdata_sanpham_all(callBack, DataUser.id, DataUser.occupation)

        return arr
    }

}