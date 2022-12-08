package com.developer.cubemarket.fragment.fragment_utils_product

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.utils.ProductFormatSizeAndColorAdapter
import com.developer.cubemarket.adapter.utils.color.ColorAdapter
import com.developer.cubemarket.adapter.utils.size.SizeAdapter
import com.developer.cubemarket.call_back_view.CallBackDelOption
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.DAO.DaoKichThuoc
import com.developer.cubemarket.connection.MODEL.DAO.DaoMauSac
import com.developer.cubemarket.connection.MODEL.DAO.DaoOption
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc
import com.developer.cubemarket.connection.MODEL.OOP.Mausac
import com.developer.cubemarket.connection.MODEL.OOP.Option
import com.developer.cubemarket.connection.callback.*
import com.developer.cubemarket.databinding.FragmentUpdateProductBinding
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker
import java.util.regex.Pattern

class UpdateProductFragment : Fragment() {
    lateinit var adapterOption: ProductFormatSizeAndColorAdapter
    private var strUpload = ""
    lateinit var adapterColor: ArrayAdapter<Mausac>
    lateinit var adapterSize: ArrayAdapter<Kichthuoc>
    private var strBase64Avatar: String? = ""
    private var detail: String? = ""
    private var brand: String? = ""
    private var amount: Int? = 0
    private var price: Int? = 0
    private var imgProduct: String? = ""
    private var nameProduct: String? = ""
    private var imgDirectory: String? = ""
    private var locationDirectory: String? = ""
    private var idProduct: Int? = 0
    lateinit var binding: FragmentUpdateProductBinding
    private var nameDirectory: String? = ""
    private val typeDirectory = arrayListOf<Danhmuc>()
    var arrOption = arrayListOf<String>()
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var sizeAdapter: SizeAdapter
        @SuppressLint("StaticFieldLeak")
        lateinit var colorAdapter: ColorAdapter

        val arrSize = arrayListOf<Kichthuoc>()
        val arrColor = arrayListOf<Mausac>()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateProductBinding.inflate(layoutInflater)
        //main code

        //init data + view (DEFAULT)
        initDataDefault()
        initSpinnerSizeAndColor()
        initDataSpinnerDirectory()


        initValidateAndUpdateProduct()
        initRecyclerOption()
        initEventAddFormat()
        initEventPickerAvatar()

        //end code
        return binding.root
    }

    private fun initEventAddFormat() {
        binding.btnAddFormat.setOnClickListener {
            val price = binding.edtPrice.text.toString()
            val amount = binding.edtAmount.text.toString()

            val idSize = arrSize[binding.spnSize.selectedItemPosition].makichthuoc
            val idColor = arrColor[binding.spnColor.selectedItemPosition].mamausac

            var isCheck = true
            if (Pattern.matches("^[0-9]{5,}$", price)){
                binding.tilPrice.error = null
            }else{
                isCheck = false
                binding.tilPrice.error = "Giá 5-10 kí tự số"
            }
            if (Pattern.matches("^[0-9]{1,10}$", amount)){
                binding.tilAmount.error = null
            }else{
                isCheck = false
                binding.tilAmount.error = "Số lượng 1-10 kí tự số"
            }
            if (isCheck){
                val formatUpload = "$idColor:$idSize:${price}:${amount}/"
                //upload this: strUpload
                strUpload += formatUpload
                //ui this: rs
                val idUiSize = arrSize[binding.spnSize.selectedItemPosition].tenkichthuoc
                val idUiColor = arrColor[binding.spnColor.selectedItemPosition].tenmausac
                val rs = "$idUiSize - $idUiColor - ${Utils.formaterVND(price.toInt())} - $amount"
                arrOption.add(rs)
                adapterOption.notifyItemInserted(arrOption.size)
            }
        }
    }

    private fun initSpinnerSizeAndColor() {
        arrSize.clear()
        adapterSize = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrSize)
        binding.spnSize.adapter = adapterSize
        val callBackSize = object : CallBackSizeProduct {
            override fun onSuccess(kt: Kichthuoc) {
                arrSize.add(kt)
                adapterSize.notifyDataSetChanged()
            }

            override fun onFail(rs: String) {
            }

            override fun onError(rs: String) {
            }
        }
        DaoKichThuoc(requireContext()).getdata_kichthuoc(callBackSize)
        arrColor.clear()
        adapterColor = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrColor)
        binding.spnColor.adapter = adapterColor
        val callBackColor = object : CallBackColorProduct {
            override fun onSuccess(ms: Mausac) {
                arrColor.add(ms)
                adapterColor.notifyDataSetChanged()
            }

            override fun onFail(rs: String) {
            }

            override fun onError(rs: String) {
            }
        }
        DaoMauSac(requireContext()).getdata_mausac(callBackColor)
    }

    private fun initRecyclerOption() {
        val callBackDel = object : CallBackDelOption{
            override fun onDel(pos: Int) {
                arrOption.removeAt(pos)
                adapterOption.notifyItemRemoved(pos)
            }
        }
        adapterOption = ProductFormatSizeAndColorAdapter(callBackDel, this, arrOption)
        binding.ryFormat.adapter = adapterOption

        val callBackOption = object : CallBackDataOption {
            override fun onSuccess(op: Option) {
//                var formatUpload = "$idSizeProduct:$idColorProduct:$price:$amount/"
                val format = "${op.size_name} - ${op.color_name} - ${Utils.formaterVND(op.price)} - ${op.number}"
                arrOption.add(format)
                adapterOption.notifyItemInserted(arrOption.size)
            }

            override fun onFail(rs: String) {
            }

            override fun onError(rs: String) {
            }

            override fun onFinish(rs: String) {

            }

        }
        DaoOption(requireContext()).getdata_option(callBackOption, idProduct!!)
    }

    private fun initValidateAndUpdateProduct() {
        binding.btnUpdate.setOnClickListener {
            var isCheck = true
            val name = binding.edtName.text.toString().trim()
            val idDirectory = typeDirectory[binding.spnDirectory.selectedItemPosition].madanhmuc
            val price = binding.edtPrice.text.toString().trim()
            val amount = binding.edtAmount.text.toString().trim()
            val brand = binding.edtBrand.text.toString().trim()
            val strSize = arrSize.joinToString(
                separator = ","
            )
            Log.d("SSS", strSize)
            val strColor = arrColor.joinToString(
                separator = ","
            )
            Log.d("SSS", strColor)
            val detail = binding.edtDetail.text.toString().trim()
            if(Pattern.matches("[${Utils.getRegexVietNam2()} \\\\,]{1,80}", name)){
                binding.tilName.error = null
            } else {
                binding.tilName.error = "Tên 1-80 kí tự, không có kí tự đặc biệt"
                isCheck = false
            }

            if (Pattern.matches("[${Utils.getRegexVietNam2()} \\\\,]{1,18}", brand)) {
                binding.tilBrand.error = null
            } else {
                binding.tilBrand.error = "Nhãn hiệu 1-18 kí tự, không có kí tự đặc biệt"
                isCheck = false
            }

            if (Pattern.matches("^[\\S ]{5,500}\$", detail)) {
                binding.tilDetail.error = null
            } else {
                isCheck = false
                binding.tilDetail.error = "Chi tiết sản phẩm 5-500 kí tự, không có kí tự đặc biệt"
            }
            if(isCheck){
                Log.d("ID DIRECTORY", idDirectory.toString())
                // Update basic
                val callBackUpdate = object: CallBackUpdateProduct {
                    override fun onSuccess(rs: String) {
                        Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                    override fun onFail(rs: String) {
                        Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(rs: String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                }
                /*
                    # CHANGE OPTION
                    1: Get id fill option
                    2: Delete fill option by id
                    3: Insert again option of array (in thread)
                 */
                val daoOption = DaoOption(requireContext())
                //Step1: Get id fill option
                val callBackGetOption = object : CallBackDataOption{
                    override fun onSuccess(op: Option) {
                        //Step2: Delete fill option by id
                        daoOption.delete_option(op.option_id)
                    }

                    override fun onFail(rs: String) {
                    }

                    override fun onError(rs: String) {
                    }

                    override fun onFinish(rs: String) {
                    }
                }
                daoOption.getdata_option(callBackGetOption, idProduct!!)

                //Step3: Insert again option of array (in thread)
                daoOption.insert_option(idProduct!!, strUpload)

                //Update basic product
                DaoSanPham(requireContext()).update_sanpham(callBackUpdate,
                    idProduct!!,
                    idDirectory,
                    name,
                    strBase64Avatar,
                    brand,
                    detail
                )
            }
        }
    }

    private fun initDataDefault() {
        idProduct = arguments?.getInt("id_product")
        nameProduct = arguments?.getString("name_product")
        nameDirectory = arguments?.getString("name_directory")
        imgProduct = arguments?.getString("img_product")
        brand = arguments?.getString("brand")
        detail = arguments?.getString("detail")

        locationDirectory = arguments?.getString("location_directory")
        imgDirectory = arguments?.getString("img_directory")



        binding.edtName.setText(nameProduct)
        binding.edtPrice.setText("$price")
        binding.edtAmount.setText("$amount")
        binding.edtBrand.setText(brand)
        binding.edtDetail.setText(detail)
        //set image default
        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(this)
            .asBitmap()
            .load(imgProduct)
            .apply(option)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    binding.imvAvatar.setImageBitmap(resource)
                    strBase64Avatar = Utils.getEncoded64ImageStringFromBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    private fun initDataSpinnerDirectory() {

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_directory_item,
            typeDirectory
        )
        val callBackDirectory = VolleyCallBack { danhmuc ->
                typeDirectory.add(danhmuc!!);
                adapter.notifyDataSetChanged();
                if (danhmuc.tendanhmuc.equals(nameDirectory)) {
                    binding.spnDirectory.setSelection(typeDirectory.size - 1)
                }
            }
        DaoDanhMuc(requireContext()).getdata_danhmuc(callBackDirectory)
        binding.spnDirectory.adapter = adapter
    }
    private fun initEventPickerAvatar() {
        binding.imvAvatar.setOnClickListener{
            val permissionCheck = ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                TedBottomPicker.with(requireActivity())
                    .setTitle("Chọn ảnh đại diện")
                    .setGalleryTileBackgroundResId(R.color.item_color_secondary)
                    .setCameraTileBackgroundResId(R.color.item_color_primary)
                    .show {
                        // here is selected image uri
                        binding.imvAvatar.setImageURI(it)
                        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
                        strBase64Avatar = Utils.getEncoded64ImageStringFromBitmap(bitmap)
                    }
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    2001
                )
            }
        }
    }
}