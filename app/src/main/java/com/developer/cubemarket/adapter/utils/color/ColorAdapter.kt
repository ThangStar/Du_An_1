package com.developer.cubemarket.adapter.utils.color

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.Mausac
import com.developer.cubemarket.databinding.SizeItemBinding

class ColorAdapter(
    private var activity: Activity,
    var arr: ArrayList<Mausac>
): RecyclerView.Adapter<ColorAdapter.SizeViewHolder>() {
    class SizeViewHolder(
        activity: Activity,
        v: View,
        arr: ArrayList<Mausac>
        ): RecyclerView.ViewHolder(v){
        val binding = SizeItemBinding.bind(v)
        init {
            v.findViewById<LinearLayout>(R.id.ln_root).setOnClickListener {
                Log.d("SSS", "POS: $adapterPosition")
                Utils.dialogDelColor(activity, "Xóa màu ${arr[adapterPosition]}?",
                    "Bạn có chắc chắn xóa màu này ?", 123, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.size_item, parent, false)
//
        return SizeViewHolder(activity, v, arr)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val size = arr[position]
        holder.binding.tvName.text = size.tenmausac

    }
    override fun getItemCount(): Int {
        return arr.size
    }

}