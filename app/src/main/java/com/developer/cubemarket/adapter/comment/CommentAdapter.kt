package com.developer.cubemarket.adapter.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.connection.MODEL.OOP.CommentProduct
import com.developer.cubemarket.databinding.CommentItemBinding

class CommentAdapter(
    var arr: ArrayList<CommentProduct>
): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = CommentItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val cp = arr[position]
        holder.binding.tvName.text = cp.ten
        holder.binding.rbComment.rating = cp.saodanhgia.toFloat()
        holder.binding.tvDate.text = cp.ngaydang
        holder.binding.tvBodyCmt.text = cp.noidungbinhluan
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}