package com.developer.cubemarket.adapter.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.call_back_view.CallBackDelOption
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.databinding.FormatItemBinding

class ProductFormatSizeAndColorAdapter(
    private var callBackDelOption: CallBackDelOption,
    var fr: Fragment,
    var arr: ArrayList<String>
) :
    RecyclerView.Adapter<ProductFormatSizeAndColorAdapter.ProductFormatSizeAndColorViewHolder>() {
    class ProductFormatSizeAndColorViewHolder(
        v: View,
        var fr: Fragment,
        private var callBackDelOption: CallBackDelOption,
        var arr: ArrayList<String>
    ) : RecyclerView.ViewHolder(v){
        val binding = FormatItemBinding.bind(v)
        init {
            binding.imvDel.setOnClickListener {
                Utils.dialogDelOption(callBackDelOption, fr.requireActivity(), "Xóa?", "Bạn muốn xóa option này",
                adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductFormatSizeAndColorViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.format_item, parent, false)
        return ProductFormatSizeAndColorViewHolder(v, fr, callBackDelOption, arr)
    }

    override fun onBindViewHolder(holder: ProductFormatSizeAndColorViewHolder, position: Int) {
        val op = arr[position]
        holder.binding.tvFormat.text = op
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}