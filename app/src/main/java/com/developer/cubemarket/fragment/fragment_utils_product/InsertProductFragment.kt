package com.developer.cubemarket.fragment.fragment_utils_product

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.utils.ProductFormatSizeAndColorAdapter
import com.developer.cubemarket.callback.CallBackColorProduct
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.databinding.FragmentPostProductBinding
import com.developer.cubemarket.callback.CallBackInsertProduct
import com.developer.cubemarket.callback.CallBackSizeProduct
import com.developer.cubemarket.callback.VolleyCallBack
import com.developer.cubemarket.connection.MODEL.DAO.DaoKichThuoc
import com.developer.cubemarket.connection.MODEL.DAO.DaoMauSac
import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc
import com.developer.cubemarket.connection.MODEL.OOP.Mausac
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker
import java.util.regex.Pattern


class InsertProductFragment : Fragment() {
    lateinit var binding: FragmentPostProductBinding
    lateinit var bitmap: Bitmap
    lateinit var adapterFormat:ProductFormatSizeAndColorAdapter
    lateinit var adapterColor: ArrayAdapter<Mausac>
    lateinit var adapterSize: ArrayAdapter<Kichthuoc>

    val type = arrayListOf<Danhmuc>()
    var arrSize = arrayListOf<Kichthuoc>()
    var arrColor = arrayListOf<Mausac>()
    val arrFormat = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostProductBinding.inflate(layoutInflater)

        initEventAddFormat()
        initDataRecyclerFormat()
        initSpinnerSizeAndColor()
        initImageDefault()
        initDataSpinnerDirectory()
        initEventPickerAvatar()
        initInsertProduct()


        return binding.root
    }

    private fun initEventAddFormat() {
        binding.btnAddFormat.setOnClickListener {
            val size = arrSize[binding.spnSize.selectedItemPosition].tenkichthuoc
            val color = arrColor[binding.spnColor.selectedItemPosition].tenmausac
            val price = binding.edtPrice.text.toString().trim()
            val amount = binding.edtAmount.text.toString().trim()
            val rs = "$size - $color - ${Utils.formaterVND(price.toInt())} - $amount"
            arrFormat.add(rs)
            adapterFormat.notifyItemInserted(arrFormat.size)
        }
    }

    private fun initDataRecyclerFormat() {
        adapterFormat = ProductFormatSizeAndColorAdapter(initDataFormat())
        binding.ryFormat.adapter = adapterFormat
    }

    private fun initDataFormat(): ArrayList<String> {
        return arrFormat
    }

    private fun initSpinnerSizeAndColor() {
        adapterSize = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrSize)
        binding.spnSize.adapter = adapterSize
        val callBackSize = object : CallBackSizeProduct{
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

        adapterColor = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrColor)
        binding.spnColor.adapter = adapterColor
        val callBackColor = object : CallBackColorProduct{
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


    private fun initImageDefault() {
        bitmap = Utils.resourceToBitmap(resources, R.drawable.other)
    }

    private fun initInsertProduct() {
        binding.btnInsert.setOnClickListener {
            var isCheck = true
            val name = binding.edtName.text.toString().trim()
            val directory = type[binding.spnDirectory.selectedItemPosition].madanhmuc
            val brand = binding.edtBrand.text.toString().trim()

            val price = binding.edtPrice.text.toString().trim()
            val amount = binding.edtAmount.text.toString().trim()

            val size = arrSize[binding.spnSize.selectedItemPosition].makichthuoc
            val color = arrColor[binding.spnColor.selectedItemPosition].mamausac
            val detail = binding.edtDetail.text.toString().trim()
            if(Pattern.matches("[${Utils.getRegexVietNam2()} \\\\,]{1,80}", name)){
                binding.tilName.error = null
            }else{
                binding.tilName.error = "Tên 1-80 kí tự, không có kí tự đặc biệt"
                isCheck = false
            }

            if(Pattern.matches("^[0-9]{5,10}$", price)){
                binding.tilPrice.error = null
            }else{
                binding.tilPrice.error = "Giá 5-10 kí tự, phải là số"
                isCheck = false
            }

            if(Pattern.matches("^[0-9]{1,10}$", amount)){
                binding.tilAmount.error = null
            }else{
                binding.tilAmount.error = "Số lượng 1-10 kí tự, phải là số"
                isCheck = false
            }

            if(Pattern.matches("[${Utils.getRegexVietNam2()} \\\\,]{1,18}", brand)){
                binding.tilBrand.error = null
            }else{
                binding.tilBrand.error = "Nhãn hiệu 1-18 kí tự, không có kí tự đặc biệt"
                isCheck = false
            }

            if(Pattern.matches("^[\\S ]{5,500}\$", detail)){
                binding.tilDetail.error = null
            }else{
                isCheck = false
                binding.tilDetail.error = "Chi tiết sản phẩm 5-500 kí tự, không có kí tự đặc biệt"
            }
            if (isCheck){
                Toasty.success(requireContext(), "Xin chờ", Toasty.LENGTH_SHORT).show()
                val call = object: CallBackInsertProduct{
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
                //format color & size
                val rs = "$color:$size:$price:$amount/"
                DaoSanPham(requireContext()).insert_sanpham(call, directory,
                    name,
                    Utils.getEncoded64ImageStringFromBitmap(bitmap),
                    brand,
                    detail,
                    rs,
                    DataUser.id)
            }
        }
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
                        bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)

                    }
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    2001
                )
            }



        }
    }

    private fun initDataSpinnerDirectory() {

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_directory_item,
            type
        )
        binding.spnDirectory.setAdapter(adapter)
        val callback = VolleyCallBack { danhmuc ->
            type.add(danhmuc!!)
            adapter.notifyDataSetChanged()
        }
        DaoDanhMuc(requireContext()).getdata_danhmuc(callback)

    }
}