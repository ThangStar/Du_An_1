package com.developer.cubemarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.DirectoryHome
import com.developer.cubemarket.databinding.DirectoryItemBinding

class DirectoryHomeAdapter(
    var arr: ArrayList<DirectoryHome>
): RecyclerView.Adapter<DirectoryHomeAdapter.DirectoryHomeViewHolder>() {
    class DirectoryHomeViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = DirectoryItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryHomeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.directory_item, parent, false)
        return DirectoryHomeViewHolder(v)
    }


    override fun onBindViewHolder(holder: DirectoryHomeViewHolder, position: Int) {
        val dr = arr[position]
        holder.binding.imvDirectory.setImageResource(dr.img)
        holder.binding.tvNameDirectory.text = dr.name
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}