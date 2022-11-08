package com.developer.cubemarket.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.developer.cubemarket.fragment.ProductFragment

class ProductPagerAdapter(fr: ProductFragment, var arr: ArrayList<Fragment>): FragmentStateAdapter(fr){
    override fun getItemCount(): Int {
        return arr.size
    }

    override fun createFragment(position: Int): Fragment {
        return arr.get(position)
    }

}