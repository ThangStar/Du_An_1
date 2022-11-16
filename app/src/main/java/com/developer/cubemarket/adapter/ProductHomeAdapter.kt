package com.developer.cubemarket.adapter

import android.content.Context
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
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.ProductHome
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.databinding.ProductHomeItemBinding
import es.dmoral.toasty.Toasty

class ProductHomeAdapter(
    var fr: Fragment,
    var arr: ArrayList<ProductHome>
): RecyclerView.Adapter<ProductHomeAdapter.ProducHomeViewHolder>() {
    class ProducHomeViewHolder(
        var arr: ArrayList<ProductHome>,
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
                        if(DataUser.occupation == 3){
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
                        Utils.dialogDelProduct(fr.requireActivity(), "Xóa ${arr[adapterPosition].nameProduct}",
                        "Bạn có muốn xóa sản phẩm ${arr[adapterPosition].nameProduct}", 123, adapterPosition)
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
//        holder.binding.imvProduct.setImageBitmap(Utils.base64ToBitMap(pr.imgProduct))
        holder.binding.tvPrice.text = Utils.formaterVND(pr.priceProduct)
        holder.binding.tvTitle.text = pr.nameProduct
        holder.binding.lnProduct.setOnClickListener {
            findNavController(fr).navigate(
                R.id.action_productFragment_to_detailProductFragment)
        }

    }

    override fun getItemCount(): Int {
        return arr.size
    }

}