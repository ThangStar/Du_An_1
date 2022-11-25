package com.developer.cubemarket.adapter.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.connection.MODEL.OOP.Option
import com.developer.cubemarket.databinding.FormatItemBinding

class ProductFormatSizeAndColorAdapter(
    var arr: ArrayList<String>
) :
    RecyclerView.Adapter<ProductFormatSizeAndColorAdapter.ProductFormatSizeAndColorViewHolder>() {
    class ProductFormatSizeAndColorViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val binding = FormatItemBinding.bind(v)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductFormatSizeAndColorViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.format_item, parent, false)
        return ProductFormatSizeAndColorViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductFormatSizeAndColorViewHolder, position: Int) {
        val op = arr[position]
        holder.binding.tvFormat.text = op
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}