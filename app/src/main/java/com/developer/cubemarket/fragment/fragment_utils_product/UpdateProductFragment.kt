package com.developer.cubemarket.fragment.fragment_utils_product

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.utils.color.ColorAdapter
import com.developer.cubemarket.adapter.utils.size.SizeAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.DAO.DaoKichThuoc
import com.developer.cubemarket.connection.MODEL.DAO.DaoMauSac
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc
import com.developer.cubemarket.connection.MODEL.OOP.Mausac
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.databinding.FragmentUpdateProductBinding
import com.developer.cubemarket.utils.CallBackColorProduct
import com.developer.cubemarket.utils.CallBackSizeProduct
import com.developer.cubemarket.utils.VolleyCallBack
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker
import java.util.regex.Pattern

class UpdateProductFragment : Fragment() {
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
    val typeDirectory = arrayListOf<Danhmuc>()
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var sizeAdapter: SizeAdapter
        @SuppressLint("StaticFieldLeak")
        lateinit var colorAdapter: ColorAdapter

        val arrSize = arrayListOf<String>()
        val arrColor = arrayListOf<String>()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateProductBinding.inflate(layoutInflater)
        //main code

        //init data + view
        initDataDefault()



        initValidate()
        initRecyclerSize()
        initRecycelrColor()
        initAddSize()
        initAddColor()
        initDataSpinnerDirectory()
        initEventPickerAvatar()
        //end code
        return binding.root
    }

    private fun initValidate() {
        binding.btnUpdate.setOnClickListener {
            var isCheck = true
            val name = binding.edtName.text.toString().trim()
            val idDirectory = typeDirectory[binding.spnDirectory.selectedItemPosition].madanhmuc.toString()
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
            if (Pattern.matches(Utils.getRegexVietNam(), name)) {
                binding.tilName.error = null
            } else {
                binding.tilName.error = "Tên 1-18 kí tự, không có kí tự đặc biệt"
                isCheck = false
            }

            if (Pattern.matches("^[0-9]{5,10}$", price)) {
                binding.tilPrice.error = null
            } else {
                binding.tilPrice.error = "Giá 5-10 kí tự, phải là số"
                isCheck = false
            }

            if (Pattern.matches("^[0-9]{1,10}$", amount)) {
                binding.tilAmount.error = null
            } else {
                binding.tilAmount.error = "Số lượng 1-10 kí tự, phải là số"
                isCheck = false
            }

            if (Pattern.matches("[${Utils.getRegexVietNam2()} \\\\,]{1,18}", brand)) {
                binding.tilBrand.error = null
            } else {
                binding.tilBrand.error = "Nhãn hiệu 1-18 kí tự, không có kí tự đặc biệt"
                isCheck = false
            }

            if (Pattern.matches("^[a-zA-Z0-9\\\\, ]{1,18}$", strSize)) {
                binding.tilSize.error = null
            } else {
                binding.tilSize.error = "Kích cỡ 1-18 kí tự, không có kí tự đặc biệt" +
                        " - ngăn cách nhau bằng dấu phẩy"
                isCheck = false
            }

            if (Pattern.matches("[${Utils.getRegexVietNam2()} \\\\,]{1,18}", strColor)) {
                binding.tilColor.error = null
            } else {
                binding.tilColor.error =
                    "Màu sắc 1-18 kí tự, không có kí tự đặc biệt - ngăn cách nhau bằng dấu phẩy"
                isCheck = false
            }

            if (Pattern.matches("^[\\S ]{5,500}\$", detail)) {
                binding.tilDetail.error = null
            } else {
                isCheck = false
                binding.tilDetail.error = "Chi tiết sản phẩm 5-500 kí tự, không có kí tự đặc biệt"
            }
            if(isCheck){
                Log.d("ID DIRECTORY", idDirectory)
                Toasty.success(requireContext(), "OK", Toasty.LENGTH_SHORT).show()
                DaoSanPham(requireContext()).update_sanpham(Sanpham(
                    idProduct!!,
                Danhmuc(idDirectory.toInt(), nameDirectory,
                    locationDirectory, imgDirectory),
                    name,
                    imgProduct,
                    brand,
                    amount.toInt(),
                    price.toInt(),
                    detail,
                    DataUser.id
                ))
            }
        }
    }

    private fun initDataDefault() {
        idProduct = arguments?.getInt("id_product")
        nameProduct = arguments?.getString("name_product")
        nameDirectory = arguments?.getString("name_directory")
        imgProduct = arguments?.getString("img_product")
        price = arguments?.getInt("price")
        amount = arguments?.getInt("amount")
        brand = arguments?.getString("brand")
        detail = arguments?.getString("detail")

        locationDirectory = arguments?.getString("location_directory")
        imgDirectory = arguments?.getString("img_directory")



        binding.edtName.setText(nameProduct)
        binding.edtPrice.setText(Utils.formaterVND(price))
        binding.edtAmount.setText("$amount")
        binding.edtBrand.setText(brand)
        binding.edtDetail.setText(detail)
        //set image default
        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(requireContext()).load(imgProduct).apply(option).into(binding.imvAvatar);

        //clear array size load data again
        arrSize.clear()
        //init data for size
        val callBackSize = object: CallBackSizeProduct{
            override fun onSuccess(kt: Kichthuoc) {
                //add data into array size
                arrSize.add(kt.tenkichthuoc)
                sizeAdapter.notifyItemInserted(arrSize.size)
            }

            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(rs: String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }
        }
        DaoKichThuoc(requireContext()).getdata_kichthuoc(callBackSize, idProduct!!)

        //clear array color load data again
        arrColor.clear()
        //init data for color

        val callBackColor = object: CallBackColorProduct{
            override fun onSuccess(ms: Mausac) {
                arrColor.add(ms.tenmausac)
                colorAdapter.notifyItemInserted(arrColor.size)
            }

            override fun onFail(rs: String) {
                Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()

            }

            override fun onError(rs: String) {
                Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
            }

        }
        DaoMauSac(requireContext()).getdata_mausac(callBackColor, idProduct!!)
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
            if(danhmuc.tendanhmuc.equals(nameDirectory)){
                binding.spnDirectory.setSelection(typeDirectory.size-1)
            }
        }
        DaoDanhMuc(requireContext()).getdata_danhmuc(callBackDirectory)
        binding.spnDirectory.adapter = adapter


    }

    private fun formatStr(arr: ArrayList<String>): String {
        var strFormat = ""
        if (arr.size != 0){
            for (i in arr){
                strFormat += i+", "
            }
            strFormat = strFormat.substring(0, strFormat.length - 2)
        }
        return strFormat
    }

    private fun initAddColor() {
            binding.btnAddColor.setOnClickListener{
                val color = binding.edtColor.text.toString().trim()
                val matches = "^[${Utils.getRegexVietNam2()}]{1,10}\$"
                if (Pattern.matches(matches, color)){
                    if(binding.tilColor.error != null){
                        binding.tilColor.error = null
                    }
                    binding.edtColor.setText("")
                    arrColor.add(color)
                    colorAdapter.notifyItemInserted(arrColor.size)
                }else{
                    binding.tilColor.error = "vd: Xanh, Đỏ.."
                }
            }
        }

        private fun initAddSize() {
            binding.btnAddSize.setOnClickListener{
                val size = binding.edtSize.text.toString().trim()
                val matches = "^[a-zA-Z0-9]{1,3}\$"
                if (Pattern.matches(matches, size)){
                    if(binding.tilSize.error != null){
                        binding.tilSize.error = null
                    }
                    binding.edtSize.setText("")
                    arrSize.add(size)
                    sizeAdapter.notifyItemInserted(arrSize.size)
                }else{
                    binding.tilSize.error = "vd: X, XL, 39.."
                }
            }
        }

        private fun initRecycelrColor() {
            colorAdapter = ColorAdapter(requireActivity(), initColor())
            binding.ryColor.adapter = colorAdapter
        }

        private fun initColor(): ArrayList<String> {
            return arrColor
        }

        private fun initRecyclerSize() {
            sizeAdapter = SizeAdapter(requireActivity(), initSize())
            binding.rySize.adapter = sizeAdapter
        }

        private fun initSize(): ArrayList<String> {
            return arrSize
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
                        Toasty.success(requireContext(), "Đã chọn", 500).show()

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