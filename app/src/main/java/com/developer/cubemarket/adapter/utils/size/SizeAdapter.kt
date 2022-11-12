package com.developer.cubemarket.adapter.utils.size

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.SizeItemBinding

class SizeAdapter(
    var arr: ArrayList<String>
): RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {
    class SizeViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = SizeItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.size_item, parent, false)
        return SizeViewHolder(v)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val size = arr[position]
        holder.binding.tvName.text = size
        holder.binding.lnSize.setOnClickListener {
            //remove this size and set data change

        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}