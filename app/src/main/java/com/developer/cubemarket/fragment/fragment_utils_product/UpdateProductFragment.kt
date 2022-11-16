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
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.utils.color.ColorAdapter
import com.developer.cubemarket.adapter.utils.size.SizeAdapter
import com.developer.cubemarket.databinding.FragmentProductBinding
import com.developer.cubemarket.databinding.FragmentUpdateProductBinding
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker
import java.util.regex.Pattern

class UpdateProductFragment : Fragment() {
    lateinit var binding: FragmentUpdateProductBinding
    @SuppressLint("StaticFieldLeak")
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

        initRecyclerSize()
        initRecycelrColor()
        initAddSize()
        initAddColor()
        initDataSpinnerDirectory()
        initEventUpdate()
        initEventPickerAvatar()
        //end code
        return binding.root
    }

    private fun initDataDefault() {

    }

    private fun initDataSpinnerDirectory() {
        val type = arrayListOf("Phổ biến", "Xu hướng", "Mới", "Bán chạy", "Khác")

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_directory_item,
            type
        )
        binding.spnDirectory.setAdapter(adapter)
    }
    private fun initEventUpdate() {
        binding.btnUpdate.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val directory = binding.spnDirectory.selectedItem.toString()
            val price = binding.edtPrice.text.toString().trim()
            val amount = binding.edtAmount.text.toString()
            val brand = binding.edtBrand.text.toString().trim()
            val size = formatStr(arrSize)
            val color = formatStr(arrColor)
            val detail = binding.edtDetail.text.toString()

            // update above data to DB

            Log.d("SSS", "name: $name, directory: $directory, " +
                    "price: $price, amount: $amount, " +
                    "brand: $brand, size: $size, color: $color, detail: $detail")
        }
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
                val matches = "^[a-zA-Z0-9]{1,10}\$"
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
            if (arrColor.size == 0){
                arrColor.add("Xanh")
                arrColor.add("Đỏ")
                arrColor.add("Vàng")
            }
            return arrColor
        }

        private fun initRecyclerSize() {
            sizeAdapter = SizeAdapter(requireActivity(), initSize())
            binding.rySize.adapter = sizeAdapter
        }

        private fun initSize(): ArrayList<String> {
            if(arrSize.size == 0){
                arrSize.add("M")
                arrSize.add("L")
                arrSize.add("X")
                arrSize.add("XL")
                arrSize.add("XXL")
            }
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