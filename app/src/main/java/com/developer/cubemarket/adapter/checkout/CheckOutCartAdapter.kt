package com.developer.cubemarket.adapter.checkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.checkout.ProductCheckOut
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.databinding.CheckoutProductItemBinding

class CheckOutCartAdapter(
var arr: ArrayList<ProductCheckOut>
): RecyclerView.Adapter<CheckOutCartAdapter.CheckOutCartViewHolder>() {
    class CheckOutCartViewHolder(v: View): RecyclerView.ViewHolder(v){
        val binding = CheckoutProductItemBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutCartViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.checkout_product_item, parent, false)
        return CheckOutCartViewHolder(v)
    }

    override fun onBindViewHolder(holder: CheckOutCartViewHolder, position: Int) {
        val pr = arr[position]
        holder.binding.imvProduct.setImageBitmap(pr.image)
        holder.binding.tvName.text = pr.name
        holder.binding.tvInfo.text = pr.info
        holder.binding.tvPrice.text = Utils.formaterVND(pr.price)
        holder.binding.tvAmount.text = "${pr.amount}"
        holder.binding.imvPlus.setOnClickListener {
            //plus tvAmount
        }
        holder.binding.imvMinus.setOnClickListener {
            //minus tvAmount

        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}