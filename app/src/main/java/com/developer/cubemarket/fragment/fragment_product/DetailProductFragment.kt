package com.developer.cubemarket.fragment.fragment_product

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.databinding.FragmentDetailProductBinding
import java.lang.String


class DetailProductFragment : Fragment() {
    lateinit var binding: FragmentDetailProductBinding
    lateinit var ctx: Context
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
        return binding.root
    }

    private fun initDataDefault() {
        val urlImg = arguments?.getString("img")
        val name = arguments?.getString("name")
        val detail = arguments?.getString("detail")
        val price = arguments?.getString("price")
        val brand = arguments?.getString("brand")
        val amount = arguments?.getInt("amount")
        binding.tvName.text = name
        binding.tvDetail.text = detail
        binding.tvPrice.text = price
        binding.tvAmount.text = "Kho: $amount"
        binding.tvBrand.text = "Nhãn hiệu: $brand"


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