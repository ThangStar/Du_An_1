package com.developer.cubemarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.ProductHome
import com.developer.cubemarket.config.utils.DataConfig
import com.developer.cubemarket.databinding.ProductHomeItemBinding

class ProductHomeAdapter(
    var fr: Fragment,
    var arr: ArrayList<ProductHome>
): RecyclerView.Adapter<ProductHomeAdapter.ProducHomeViewHolder>() {
    class ProducHomeViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = ProductHomeItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducHomeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_home_item, parent, false);
        return ProducHomeViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProducHomeViewHolder, position: Int) {
        val pr = arr[position]
        holder.binding.imvProduct.setImageBitmap(pr.imgProduct)
        holder.binding.tvPrice.text = DataConfig.formaterVND(pr.priceProduct)
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