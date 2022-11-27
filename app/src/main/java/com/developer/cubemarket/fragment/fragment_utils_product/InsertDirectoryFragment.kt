package com.developer.cubemarket.fragment.fragment_utils_product

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.developer.cubemarket.R
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.databinding.FragmentInsertDirectoryBinding
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker
import java.util.regex.Pattern


class InsertDirectoryFragment : Fragment() {
    lateinit var binding: FragmentInsertDirectoryBinding
    lateinit var bitmap: Bitmap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertDirectoryBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        initImageDefault()
        initEventInsertDirectory()
        initEventPickerAvatar()
        return binding.root
    }

    private fun initImageDefault() {
        bitmap = Utils.resourceToBitmap(resources, R.drawable.image_default)
    }

    private fun initEventInsertDirectory() {
        binding.btnInsert.setOnClickListener {
            var isCheck = true
            val name = binding.edtName.text.toString().trim()
            val local = binding.edtLocal.text.toString().trim()

            if(Pattern.matches("^[a-zA-Z]{1,18}$", name)){
                binding.edtName.error = null
            }else{
                binding.edtName.error = "Tên danh mục cần 1-18 kí tự a-z A-Z"
                isCheck = false
            }
            if(Pattern.matches("^[a-zA-Z]{1,18}$", local)){
                binding.edtLocal.error = null
            }else{
                binding.edtLocal.error = "Tên khu vực cần 1-14 kí tự a-z A-Z"
                isCheck = false
            }

            if(isCheck){
                DaoDanhMuc(requireContext()).
                insert_danhmuc(requireContext(), Danhmuc(2, name, local,
                    DaoDanhMuc(requireContext()).getEncoded64ImageStringFromBitmap(bitmap)))
            }
        }
    }
    private fun initEventPickerAvatar() {
        binding.imvDirectory.setOnClickListener{
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
                        binding.imvDirectory.setImageURI(it)
                        bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
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