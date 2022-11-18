package com.developer.cubemarket.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.ProductPagerAdapter
import com.developer.cubemarket.databinding.FragmentProductBinding
import com.developer.cubemarket.fragment.fragment_home_pager.CartFragment
import com.developer.cubemarket.fragment.fragment_home_pager.HomeFragment
import com.developer.cubemarket.fragment.fragment_home_pager.ProfileFragment
import com.developer.cubemarket.fragment.fragment_home_pager.SearchFragment
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import kotlin.system.exitProcess

class ProductFragment : Fragment() {
    companion object{
        lateinit var binding: FragmentProductBinding
    }
    lateinit var ctx: Context
    var isExit = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater)
        ctx = binding.root.context

        val adapter = ProductPagerAdapter(this, getFragment())
        binding.pagerProduct.adapter = adapter

        initBadgeBottom()
        initOnPagerChange()
        initOnBottomNavChange()
        return binding.root
    }

    private fun initBadgeBottom() {
        val badge = binding.bottomNavProduct.getOrCreateBadge(R.id.ic_cart)
        badge.isVisible = true
        badge.number = 9
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

    private fun getFragment(): ArrayList<Fragment>{
        val arr = arrayListOf<Fragment>()
        arr.add(HomeFragment())
        arr.add(SearchFragment())
        arr.add(CartFragment())
        arr.add(ProfileFragment())

        return arr
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        class blockGoBack : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toasty.warning(ctx, "Quay lại lần nữa để thoát", Toasty.LENGTH_SHORT).show()
                GlobalScope.launch {
                    isExit = true
                    withContext(Dispatchers.IO) {
                        Thread.sleep(2000)
                    }
                    isExit = false
                    cancel()
                }
                if (isExit){
                    exitProcess(0)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, blockGoBack());
    }
}