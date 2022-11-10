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

        initEventToolbar()
        return binding.root
    }

    private fun initEventToolbar() {
        val v = getToolbarLogoIcon(binding.toolbar)
        v!!.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    fun getToolbarLogoIcon(toolbar: androidx.appcompat.widget.Toolbar): View? {
        //check if contentDescription previously was set
        val hadContentDescription = TextUtils.isEmpty(toolbar.getLogoDescription())
        val contentDescription =
            String.valueOf(if (!hadContentDescription) toolbar.getLogoDescription() else "logoContentDescription")
        toolbar.setLogoDescription(contentDescription)
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
        if (hadContentDescription) toolbar.setLogoDescription(null)
        return logoIcon
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allowEnterTransitionOverlap = false
        allowReturnTransitionOverlap = false
    }
}