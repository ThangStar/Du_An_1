package com.developer.cubemarket.fragment.fragment_utils_product

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentPostProductBinding
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker


class PostProductFragment : Fragment() {
    lateinit var binding: FragmentPostProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostProductBinding.inflate(layoutInflater)
        initDataSpinnerDirectory()
        initEventPickerAvatar()

        return binding.root
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

    private fun initDataSpinnerDirectory() {
        val type = arrayListOf("Phổ biến", "Xu hướng", "Mới", "Bán chạy", "Khác")

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_directory_item,
            type
        )
        binding.spnDirectory.setAdapter(adapter)
    }
}