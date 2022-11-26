package com.developer.cubemarket.adapter.detail_product

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.call_back_view.CallBackUpdateDetailProduct
import com.developer.cubemarket.call_back_view.OnChipSelected
import com.developer.cubemarket.connection.MODEL.OOP.Option
import com.developer.cubemarket.databinding.SizeDetailItemBinding

class DetailSizeProductAdapter(
    var fr: Fragment,
    var onChipSelected: OnChipSelected,
    var arr: ArrayList<Option>
): RecyclerView.Adapter<DetailSizeProductAdapter.DetailSizeProductViewHolder>() {
    class DetailSizeProductViewHolder(
        v: View,
        var arr: ArrayList<Option>,
        var onChipSelected: OnChipSelected,
        var fr: Fragment,
    ): RecyclerView.ViewHolder(v){
        val binding = SizeDetailItemBinding.bind(v)
        init {
            binding.chipSize.setOnClickListener {
                if (binding.chipSize.isChecked){
                    onChipSelected.onSelected(arr[adapterPosition])
                    //get data and show price
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSizeProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.size_detail_item, parent, false)
        return DetailSizeProductViewHolder(v, arr, onChipSelected, fr)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailSizeProductViewHolder, position: Int) {
        val op = arr[position]
        holder.binding.chipSize.text = "Size: ${op.size_name} Màu: ${op.color_name}"
    }

    override fun getItemCount(): Int {
       return arr.size
    }
}