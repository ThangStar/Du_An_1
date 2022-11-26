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
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.detail_product.DetailSizeProductAdapter
import com.developer.cubemarket.adapter.detail_product_similar.ProductDetailSimilarAdapter
import com.developer.cubemarket.call_back_view.CallBackProductDetailScrollTop
import com.developer.cubemarket.call_back_view.CallBackUpdateDetailProduct
import com.developer.cubemarket.call_back_view.OnChipSelected
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.DAO.DaoOption
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Option
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.connection.callback.CallBackAddCart
import com.developer.cubemarket.connection.callback.CallBackDataOption
import com.developer.cubemarket.connection.callback.CallBackProductSimilar
import com.developer.cubemarket.databinding.FragmentDetailProductBinding
import com.google.android.material.transition.MaterialContainerTransform
import es.dmoral.toasty.Toasty
import java.lang.String


class DetailProductFragment : Fragment() {
    val arrOption = arrayListOf<Option>()
    var opSelected: Option? = null
    lateinit var binding: FragmentDetailProductBinding
    lateinit var adapterDetailSize: DetailSizeProductAdapter
    lateinit var ctx: Context
    lateinit var adapterProductDetailSimilar: ProductDetailSimilarAdapter
    var idProduct: Int = 0

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


        initTransition()
        initDataDefault()

        initRecyclerSize()
        initAddCart()
        initEventToolbar()

        initRecyclerProductSimilar()
        return binding.root
    }

    private fun initAddCart() {
        binding.btnAddCart.setOnClickListener {
            if (opSelected == null){
                Toasty.warning(requireContext(), "Chưa chọn loại hàng", Toasty.LENGTH_SHORT).show()
            }else{
                val callBackAddCart = object : CallBackAddCart{
                    override fun onSuccess(rs: kotlin.String) {
                        Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onFail(err: kotlin.String) {
                        Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(error: kotlin.String) {
                        Toasty.error(requireContext(), error, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoGioHang(requireContext()).insert_giohang(callBackAddCart, DataUser.id, opSelected!!.option_id)
            }
        }
    }

    private fun initRecyclerSize() {
        val onChipSelected = object : OnChipSelected{
            override fun onSelected(op: Option) {
                opSelected = op
                binding.tvPrice.text = Utils.formaterVND(op.price)
            }
        }
        adapterDetailSize = DetailSizeProductAdapter(this, onChipSelected, initOption())
        binding.rySize.adapter = adapterDetailSize
    }

    private fun initOption(): ArrayList<Option> {
        val callBackOption = object : CallBackDataOption{
            override fun onSuccess(op: Option) {
                arrOption.add(op)
                adapterDetailSize.notifyItemInserted(arrOption.size)
            }

            override fun onFail(rs: kotlin.String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(rs: kotlin.String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
        }
//        Toasty.success(requireContext(), "mã sp là: $idProduct", Toasty.LENGTH_SHORT).show()
        DaoOption(requireContext()).getdata_option(callBackOption, idProduct)
        return arrOption
    }

    private fun initTransition() {
        binding.appBarImage.transitionName = "IMAGE_PRODUCT"

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            scrimColor = Color.TRANSPARENT
            duration = 800
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            scrimColor = Color.TRANSPARENT
            duration = 800
        }
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
                idProduct = sp.masanpham
                arrOption.clear()
                val callBackOption = object : CallBackDataOption{
                    override fun onSuccess(op: Option) {
                        arrOption.add(op)
                        Toasty.warning(requireContext(), "ID PRODUCT: "+idProduct, Toasty.LENGTH_SHORT).show()
                        adapterDetailSize.notifyDataSetChanged()
                    }

                    override fun onFail(rs: kotlin.String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(rs: kotlin.String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoOption(requireContext()).getdata_option(callBackOption, idProduct)

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

        val callBackLoadProduct = object: CallBackProductSimilar {
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
        idProduct = arguments?.getInt("id_product")!!
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
            requireActivity().onBackPressed()
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