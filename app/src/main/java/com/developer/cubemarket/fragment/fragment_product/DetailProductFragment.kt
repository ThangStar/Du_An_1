package com.developer.cubemarket.fragment.fragment_product

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.developer.cubemarket.adapter.detail_product_similar.ProductDetailSimilarAdapter
import com.developer.cubemarket.call_back_view.CallBackProductDetailScrollTop
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.databinding.FragmentDetailProductBinding
import com.developer.cubemarket.utils.CallBackProductSimilar
import es.dmoral.toasty.Toasty
import java.lang.String


class DetailProductFragment : Fragment() {
    lateinit var binding: FragmentDetailProductBinding
    lateinit var ctx: Context
    lateinit var adapterProductDetailSimilar: ProductDetailSimilarAdapter

    var nameProduct = ""
    var nameDirectory = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentDetailProductBinding.inflate(layoutInflater)
        ctx = binding.root.context

        //set tint icon floating favorite
        binding.fabAddFavorite.setColorFilter(Color.WHITE)

        initDataDefault()
        initEventToolbar()

        initRecyclerProductSimilar()
        return binding.root
    }

    private fun initRecyclerProductSimilar() {

        val callBackScrollTop = object: CallBackProductDetailScrollTop{
            override fun startScroll(sp: Sanpham) {

                //change data
                binding.tvName.text = sp.tensanpham
                binding.tvDetail.text = sp.chitiet
                binding.tvPrice.text = Utils.formaterVND(sp.giaban)
                binding.tvAmount.text = "${sp.soluong}"
                binding.tvBrand.text = sp.nhasanxuat


                try{
                    val option = Utils.getOptionLoadImgDirectoryFromUrl()
                    Glide.with(requireContext()).load(sp.img).apply(option).into(binding.appBarImage);
                }catch (_: java.lang.Exception){

                }


                //go scroll on top
                binding.appbar.setExpanded(true, true)
                binding.scrollBody.fullScroll(ScrollView.FOCUS_UP)
            }

        }
        adapterProductDetailSimilar = ProductDetailSimilarAdapter(this,callBackScrollTop, initDataProductSimilar())
        binding.ryProductSimilar.adapter = adapterProductDetailSimilar

    }

    private fun initDataProductSimilar(): ArrayList<Sanpham>{
        val arr = arrayListOf<Sanpham>()

        val callBackLoadProduct = object: CallBackProductSimilar{
            override fun onSuccess(sp: Sanpham) {
                arr.add(sp)

                adapterProductDetailSimilar.notifyItemInserted(arr.size)
            }

            override fun onFail(rs: kotlin.String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(rs: kotlin.String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

        }

        DaoSanPham(requireContext()).sanpham_tuongtu(callBackLoadProduct,
            DataUser.id,
            DataUser.occupation,
            nameDirectory,
            nameProduct
        )
        return arr
    }

    private fun initDataDefault() {
        val urlImg = arguments?.getString("img")
        nameProduct = arguments?.getString("name")!!
        val detail = arguments?.getString("detail")
        val price = arguments?.getInt("price")
        val brand = arguments?.getString("brand")
        val amount = arguments?.getInt("amount")
        nameDirectory = arguments?.getString("directory")!!

        binding.tvName.text = nameProduct
        binding.tvDetail.text = detail
        binding.tvPrice.text = Utils.formaterVND(price)
        binding.tvAmount.text = "$amount"
        binding.tvBrand.text = brand

        try{
            val option = Utils.getOptionLoadImgDirectoryFromUrl()
            Glide.with(requireContext()).load(urlImg).apply(option).into(binding.appBarImage);
        }catch (_: java.lang.Exception){

        }
    }

    private fun initEventToolbar() {
        val v = getToolbarLogoIcon(binding.toolbar)
        v!!.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun getToolbarLogoIcon(toolbar: androidx.appcompat.widget.Toolbar): View? {
        //check if contentDescription previously was set
        val hadContentDescription = TextUtils.isEmpty(toolbar.logoDescription)
        val contentDescription =
            String.valueOf(if (!hadContentDescription) toolbar.logoDescription else "logoContentDescription")
        toolbar.logoDescription = contentDescription
        val potentialViews = ArrayList<View>()
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(
            potentialViews,
            contentDescription,
            View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION
        )
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        var logoIcon: View? = null
        if (potentialViews.size > 0) {
            logoIcon = potentialViews[0]
        }
        //Clear content description if not previously present
        if (hadContentDescription) toolbar.logoDescription = null
        return logoIcon
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allowEnterTransitionOverlap = false
        allowReturnTransitionOverlap = false
    }
}