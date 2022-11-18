package com.developer.cubemarket.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.ProductHome
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.databinding.ProductHomeItemBinding
import es.dmoral.toasty.Toasty

class ProductHomeAdapter(
    var fr: Fragment,
    var arr: ArrayList<Sanpham>
): RecyclerView.Adapter<ProductHomeAdapter.ProducHomeViewHolder>() {
    class ProducHomeViewHolder(
        var arr: ArrayList<Sanpham>,
        var fr: Fragment,
        v: View): RecyclerView.ViewHolder(v),
        View.OnLongClickListener {
        val binding = ProductHomeItemBinding.bind(v)
        init {
            binding.lnProduct.setOnLongClickListener(this)
        }
        override fun onLongClick(p0: View?): Boolean {
            if (p0 != null) {
                when(p0.id){
                    binding.lnProduct.id -> {
                        if(DataUser.occupation == 2){
                            showMenu(fr.requireContext(), binding.imvProduct, R.menu.menu_utils)
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
                        Utils.dialogDelProduct(fr.requireActivity(), "Xóa ${arr[adapterPosition].tensanpham}?",
                        "Bạn có muốn xóa sản phẩm ${arr[adapterPosition].tensanpham}", arr[adapterPosition].masanpham, adapterPosition)
                    }
                    R.id.it_edit ->{
                            fr.findNavController().navigate(R.id.action_productFragment_to_updateProductFragment)
                    }
                }
                true
            }
            // Show the popup menu.
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducHomeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_home_item, parent, false);
        return ProducHomeViewHolder(arr, fr, v)
    }

    override fun onBindViewHolder(holder: ProducHomeViewHolder, position: Int) {
        val pr = arr[position]
        try{
            val option = Utils.getOptionLoadImgDirectoryFromUrl()
            Glide.with(fr.requireContext()).load(pr.img).apply(option).into(holder.binding.imvProduct);
        }catch (_: java.lang.Exception){

        }
        holder.binding.tvPrice.text = Utils.formaterVND(pr.giaban)
        holder.binding.tvTitle.text = pr.tensanpham
        holder.binding.lnProduct.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("img", pr.img)
            bundle.putString("name", pr.tensanpham)
            bundle.putString("detail", pr.chitiet)
            bundle.putInt("price", pr.giaban)
            bundle.putString("brand", pr.nhasanxuat)
            bundle.putInt("amount", pr.soluong)

            findNavController(fr).navigate(
                R.id.action_productFragment_to_detailProductFragment, bundle)
        }

    }

    override fun getItemCount(): Int {
        return arr.size
    }

}