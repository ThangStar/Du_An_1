package com.developer.cubemarket.adapter.sale

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.cart.CartProduct
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.databinding.ProductCartItemBinding
import com.developer.cubemarket.databinding.ProductSaleItemBinding

class SaleProductAdapter(
    var ctx: Context,
    var arr: ArrayList<Sanpham>
): RecyclerView.Adapter<SaleProductAdapter.SaleProductViewHolder>() {
    class SaleProductViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = ProductSaleItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_sale_item, parent, false)
        return SaleProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: SaleProductViewHolder, position: Int) {
        val pr = arr[position]
//        holder.binding.imvProduct.setImageBitmap(pr.img)
        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(ctx).load(pr.img).apply(option).into(holder.binding.imvProduct);
        holder.binding.tvAmount.text = pr.chitiet
        holder.binding.tvName.text = pr.tensanpham
        holder.binding.tvPrice.text = Utils.formaterVND(pr.giaban)
    }
    override fun getItemCount(): Int {
        return arr.size
    }

}