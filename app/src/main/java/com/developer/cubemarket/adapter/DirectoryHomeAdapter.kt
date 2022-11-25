package com.developer.cubemarket.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.Danhmuc
import com.developer.cubemarket.connection.callback.CallBackDelDirectory
import com.developer.cubemarket.databinding.DirectoryItemBinding

class DirectoryHomeAdapter(
    var ctx: Context,
    var fr: Fragment,
    var arr: ArrayList<Danhmuc>
): RecyclerView.Adapter<DirectoryHomeAdapter.DirectoryHomeViewHolder>() {
    class DirectoryHomeViewHolder(
        var adapter: DirectoryHomeAdapter,
        var arr: ArrayList<Danhmuc>,
        var fr: Fragment,
        v: View
    ): RecyclerView.ViewHolder(v), View.OnLongClickListener {
        val binding = DirectoryItemBinding.bind(v)
        init {
            binding.lnDirectory.setOnLongClickListener(this)
        }

        override fun onLongClick(p0: View?): Boolean {
            if (p0 != null){
                when(p0.id){
                    binding.lnDirectory.id ->{
                        if(DataUser.occupation == 2){
                            showMenu(fr.requireContext(), binding.imvDirectory, R.menu.menu_utils)
                        }
                    }
                }
            }
            return true
        }

        fun showMenu(ctx: Context, v: View, @MenuRes menuRes: Int) {
            val popup = PopupMenu(ctx, v)
            popup.menuInflater.inflate(menuRes, popup.menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.it_del ->{
                        val callBackOnSuccess = object : CallBackDelDirectory {
                            override fun onUpdateScreen() {
                                arr.removeAt(adapterPosition)
                                adapter.notifyItemRemoved(adapterPosition)
                            }
                        }
                        Utils.dialogDelDirectory(callBackOnSuccess, fr.requireActivity(), "Xóa ${arr[adapterPosition].tendanhmuc}?",
                            "Bạn có muốn xóa danh mục ${arr[adapterPosition].tendanhmuc}",
                            arr[adapterPosition].madanhmuc,
                            adapterPosition)
                    }
                    R.id.it_edit ->{
                        //goEdit
                        val bundle = Bundle()
                        bundle.putString("name", arr[adapterPosition].tendanhmuc)
                        bundle.putString("local", arr[adapterPosition].khuvuc)
                        bundle.putInt("id", arr[adapterPosition].madanhmuc)
                        bundle.putString("img", arr[adapterPosition].img)
                        fr.findNavController().navigate(R.id.action_productFragment_to_updateDirectoryFragment, bundle)
                    }
                }
                true
            }
            // Show the popup menu.
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryHomeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.directory_item, parent, false)
        return DirectoryHomeViewHolder(this,arr, fr, v)
    }

    override fun onBindViewHolder(holder: DirectoryHomeViewHolder, position: Int) {
        val dr = arr[position]
        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(ctx).load(dr.img).apply(option).into(holder.binding.imvDirectory);
//        holder.binding.imvDirectory.setImageBitmap(báes)
        holder.binding.tvNameDirectory.text = dr.tendanhmuc
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}