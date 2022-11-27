package com.developer.cubemarket.adapter.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.databinding.ProductCartItemBinding
import com.developer.cubemarket.fragment.fragment_home_pager.CartFragment

class CartProductAdapter(
    var fr: CartFragment,
    var arr: ArrayList<GioHang>
): RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {
    class CartProductViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = ProductCartItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_cart_item, parent, false)
        return CartProductViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val gh = arr[position]
        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(fr.requireContext()).load(gh.img).apply(option).into(holder.binding.imvProduct);
        holder.binding.tvInfo.text = "Kích thước: ${gh.tenkichthuoc} màu: ${gh.tenmau}"
        holder.binding.tvName.text = gh.tensanpham
        holder.binding.tvPrice.text = Utils.formaterVND(gh.gia)
    }
    override fun getItemCount(): Int {
        return arr.size
    }

}