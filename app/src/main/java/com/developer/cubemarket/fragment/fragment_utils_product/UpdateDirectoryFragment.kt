package com.developer.cubemarket.fragment.fragment_utils_product

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.developer.cubemarket.R
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.callback.CallbackUpdateDirectory
import com.developer.cubemarket.databinding.FragmentUpdateDirectoryBinding
import es.dmoral.toasty.Toasty
import gun0912.tedbottompicker.TedBottomPicker
import java.util.regex.Pattern


class UpdateDirectoryFragment : Fragment() {
    lateinit var binding: FragmentUpdateDirectoryBinding
    lateinit var bitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateDirectoryBinding.inflate(layoutInflater)

        //main
        initDataDefault()
        initEventUpdateDirectory()
        initEventPickerAvatar()

        //end
        return binding.root
    }
    private fun initDataDefault() {
        val name = arguments?.getString("name")
        val local = arguments?.getString("local")
        val img = arguments?.getString("img")
        val id = arguments?.getInt("id")

        binding.edtName.setText(name)
        binding.edtLocal.setText(local)
        binding.edtId.setText(id.toString())

        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(this)
            .asBitmap()
            .load(img)
            .apply(option)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    binding.imvDirectory.setImageBitmap(resource)
                    bitmap = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }
    private fun initEventUpdateDirectory() {
        binding.btnUpdate.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val local = binding.edtLocal.text.toString().trim()
            var isCheck = true
            if(Pattern.matches(Utils.getRegexVietNam(), name)){
                binding.tilName.error = null
            }else{
                binding.tilName.error = "T??n danh m???c c???n 1-18 k?? t??? v?? kh??ng c?? k?? t??? ?????c bi???t"
                isCheck = false
            }
            if(Pattern.matches(Utils.getRegexVietNam(), local)){
                binding.tilLocal.error = null
            }else{
                binding.tilLocal.error = "T??n khu v???c c???n 1-18 k?? t??? v?? kh??ng c?? k?? t??? ?????c bi???t"
                isCheck = false
            }

            if(isCheck){

                val stateUpdate = object : CallbackUpdateDirectory {
                    override fun onSuccess() {
                        Toasty.success(requireContext(), "C???p nh???t th??nh c??ng", Toasty.LENGTH_SHORT).show()
                    }

                    override fun onFail() {
                        Toasty.warning(requireContext(), "C???p nh???t kh??ng th??nh c??ng", Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        Toasty.error(requireContext(), "???? x???y ra l???i", Toasty.LENGTH_SHORT).show()
                    }

                }
                DaoDanhMuc(requireContext()).update_danhmuc(stateUpdate, arguments?.getInt("id")?.let { it1 ->
                    Danhmuc(
                        it1, name, local,
                        DaoDanhMuc(requireContext()).getEncoded64ImageStringFromBitmap(bitmap))
                })
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
                    .setTitle("Ch???n ???nh ?????i di???n")
                    .setGalleryTileBackgroundResId(R.color.item_color_secondary)
                    .setCameraTileBackgroundResId(R.color.item_color_primary)
                    .show {
                        // here is selected image uri
                        binding.imvDirectory.setImageURI(it)
                        bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
                        Toasty.success(requireContext(), "???? ch???n", 500).show()

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