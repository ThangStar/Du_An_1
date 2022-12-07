package com.developer.cubemarket.adapter.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.ColorAndSizeManagerItemBinding

class ItemColorAndSizeManagerAdapter(
    var arr: ArrayList<String>
):
    RecyclerView.Adapter<ItemColorAndSizeManagerAdapter.ItemColorAndSizeViewHolder>() {
    class ItemColorAndSizeViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = ColorAndSizeManagerItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemColorAndSizeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.color_and_size_manager_item, parent, false)
        return ItemColorAndSizeViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemColorAndSizeViewHolder, position: Int) {
        val strData = arr[position]
        holder.binding.tvName.text = strData
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}