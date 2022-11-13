package com.developer.cubemarket.adapter.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.cart.CartProduct
import com.developer.cubemarket.config.utils.utils
import com.developer.cubemarket.databinding.ProductCartItemBinding

class CartProductAdapter(
    var arr: ArrayList<CartProduct>
): RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {
    class CartProductViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = ProductCartItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_cart_item, parent, false)
        return CartProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val pr = arr[position]
        holder.binding.imvProduct.setImageBitmap(pr.img)
        holder.binding.tvInfo.text = pr.info
        holder.binding.tvName.text = pr.name
        holder.binding.tvPrice.text = utils.formaterVND(pr.price)
    }
    override fun getItemCount(): Int {
        return arr.size
    }

}