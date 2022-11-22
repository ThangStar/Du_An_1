package com.developer.cubemarket.adapter.bottom_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.utils.AddressUser
import com.developer.cubemarket.databinding.AddressItemBinding

class BottomSheetChooseAddressAdapter(
    var arr: ArrayList<AddressUser>
): RecyclerView.Adapter<BottomSheetChooseAddressViewHolder>(
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetChooseAddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
        return BottomSheetChooseAddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: BottomSheetChooseAddressViewHolder, position: Int) {
        val address = arr[position]
        holder.binding.tvAddress.text = address.address
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}
class BottomSheetChooseAddressViewHolder(v: View): RecyclerView.ViewHolder(v){
    val binding = AddressItemBinding.bind(v)
}