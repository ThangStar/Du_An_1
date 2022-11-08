package com.developer.cubemarket.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.ProductPagerAdapter
import com.developer.cubemarket.databinding.FragmentProductBinding
import com.developer.cubemarket.fragment.fragment_sub.CartFragment
import com.developer.cubemarket.fragment.fragment_sub.HomeFragment
import com.developer.cubemarket.fragment.fragment_sub.ProfileFragment
import com.developer.cubemarket.fragment.fragment_sub.SearchFragment

class ProductFragment : Fragment() {
    lateinit var binding: FragmentProductBinding
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater)
        ctx = binding.root.context

        val adapter = ProductPagerAdapter(this, getFragment())
        binding.pagerProduct.adapter = adapter

        initOnPagerChange()
        initOnBottomNavChange()
        return binding.root
    }

    private fun initOnBottomNavChange() {
        binding.bottomNavProduct.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> binding.pagerProduct.currentItem = 0
                R.id.ic_search -> binding.pagerProduct.currentItem = 1
                R.id.ic_cart -> binding.pagerProduct.currentItem = 2
                R.id.ic_profile -> binding.pagerProduct.currentItem = 3
                else -> Toast.makeText(ctx, "ANY ERROR!", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun initOnPagerChange() {
        binding.pagerProduct.registerOnPageChangeCallback(object: OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> binding.bottomNavProduct.selectedItemId = R.id.ic_home
                    1 -> binding.bottomNavProduct.selectedItemId = R.id.ic_search
                    2 -> binding.bottomNavProduct.selectedItemId = R.id.ic_cart
                    3 -> binding.bottomNavProduct.selectedItemId = R.id.ic_profile
                }
            }
        })
    }

    fun getFragment(): ArrayList<Fragment>{
        var arr = arrayListOf<Fragment>()
        arr.add(HomeFragment())
        arr.add(SearchFragment())
        arr.add(CartFragment())
        arr.add(ProfileFragment())

        return arr
    }
}