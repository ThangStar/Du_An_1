package com.developer.cubemarket.fragment.fragment_product

import android.content.Context
import android.content.res.Resources.Theme
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.comment.CommentAdapter
import com.developer.cubemarket.adapter.detail_product.DetailSizeProductAdapter
import com.developer.cubemarket.adapter.detail_product_similar.ProductDetailSimilarAdapter
import com.developer.cubemarket.call_back_view.CallBackProductDetailScrollTop
import com.developer.cubemarket.call_back_view.CallBackUpdateDetailProduct
import com.developer.cubemarket.call_back_view.OnChipSelected
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoCommentProduct
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.DAO.DaoOption
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.CommentProduct
import com.developer.cubemarket.connection.MODEL.OOP.Option
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.connection.callback.*
import com.developer.cubemarket.databinding.FragmentDetailProductBinding
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialContainerTransform
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern


class DetailProductFragment : Fragment() {
    val arrCmt = arrayListOf<CommentProduct>()
    lateinit var adapterComment: CommentAdapter
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

        initStateBtnPostCmt()

        initRecyclerSize()
        initAddCart()
        initEventToolbar()

        initRecyclerComment()
        initEventInsertCmt()



        initRecyclerProductSimilar()
        return binding.root
    }

    private fun initStateBtnPostCmt() {
        binding.edtCmt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.trim().isBlank()) {
                    binding.btnPostCmt.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary_item))
                }else{
                    binding.btnPostCmt.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_item_twice))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun initEventInsertCmt() {
        binding.btnPostCmt.setOnClickListener {
            val bodyCmt = binding.edtCmt.text.toString().trim()
            Log.d("CMT", bodyCmt)
            if(Pattern.matches("^[\\S ]{1,100}$", bodyCmt)){
                insertCmt(bodyCmt)
                binding.iplCmt.error = null
            }else{
                binding.iplCmt.error = "Bạn chưa nhập nội dung"
            }
        }
    }

    private fun insertCmt(cmt: String) {
        val callBackInsert = object : CallBackInsertCmt{
            override fun onSuccess() {
                var currentComment = CommentProduct()
                DaoCommentProduct(requireContext()).getdata_comment(object : CallBackGetComment{
                    override fun onFinish() {
                        Log.d("CURRENT CMT: ", currentComment.noidungbinhluan)
                        arrCmt.add(currentComment)
                        adapterComment.notifyItemInserted(arrCmt.size)
                    }

                    override fun onSuccess(cmt: CommentProduct) {
                        Toasty.success(requireContext(), "Cảm ơn đánh giá của bạn").show()
                        currentComment = cmt
                    }

                    override fun onFail(rs: String) {
                        Toasty.warning(requireContext(), rs).show()
                    }

                    override fun onError(rs: String) {
                        Toasty.error(requireContext(), rs).show()
                    }
                }, idProduct)
            }

            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs).show()
            }

            override fun onError(rs: String) {
                Toasty.error(requireContext(), rs).show()
            }
        }
        DaoCommentProduct(requireContext()).insert_comment(callBackInsert, idProduct, DataUser.id, cmt, binding.rtCmt.rating)
    }

    private fun initRecyclerComment() {
        adapterComment = CommentAdapter(initDataComment())
        binding.ryComment.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        binding.ryComment.adapter = adapterComment
    }

    private fun initDataComment(): ArrayList<CommentProduct> {
        Log.d("ID: ", "$idProduct")
        val callBackGet = object : CallBackGetComment{
            override fun onFinish() {

            }

            override fun onSuccess(cmt: CommentProduct) {
                arrCmt.add(cmt)
                adapterComment.notifyItemInserted(arrCmt.size)
                Log.d("CMT: ", cmt.noidungbinhluan)
            }

            override fun onFail(rs: kotlin.String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(rs: kotlin.String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
        }
        DaoCommentProduct(requireContext()).getdata_comment(callBackGet, idProduct)
        return arrCmt
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

            override fun onFinish(rs: String) {

            }
        }
//        Toasty.success(requireContext(), "mã sp là: $idProduct", Toasty.LENGTH_SHORT).show()
        DaoOption(requireContext()).getdata_option(callBackOption, idProduct)
        Log.d("SSS ID PRRRRRRR", "$idProduct")
        return arrOption
    }

    private fun initTransition() {
        binding.appBarImage.transitionName = "IMAGE_PRODUCT"
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            scrimColor = Color.TRANSPARENT
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            scrimColor = Color.TRANSPARENT
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
                arrCmt.clear()
                adapterComment.notifyDataSetChanged()
                initDataComment()
                val callBackOption = object : CallBackDataOption{
                    override fun onSuccess(op: Option) {
                        arrOption.add(op)
                        adapterDetailSize.notifyDataSetChanged()
                    }

                    override fun onFail(rs: kotlin.String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(rs: kotlin.String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onFinish(rs: String) {

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
        val rating = arguments?.getString("rating")!!.toFloat()
        binding.rbRating.rating = rating


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
    private fun getToolbarLogoIcon(toolbar: Toolbar): View? {
        //check if contentDescription previously was set
        val hadContentDescription = TextUtils.isEmpty(toolbar.logoDescription)
        val contentDescription =
            (if (!hadContentDescription) toolbar.logoDescription else "logoContentDescription").toString()
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