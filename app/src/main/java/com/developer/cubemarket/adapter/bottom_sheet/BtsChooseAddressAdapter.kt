package com.developer.cubemarket.adapter.bottom_sheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.`object`.utils.AddressUser
import com.developer.cubemarket.call_back_view.OnAddressSelected
import com.developer.cubemarket.connection.MODEL.OOP.Diachi
import com.developer.cubemarket.databinding.AddressItemBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsChooseAddressFragment

class BottomSheetChooseAddressAdapter(
    var fr: BtsChooseAddressFragment,
    var arr: ArrayList<Diachi>,
    var onAddressSelected: OnAddressSelected
): RecyclerView.Adapter<BottomSheetChooseAddressViewHolder>(
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetChooseAddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.address_item, parent, false)
        return BottomSheetChooseAddressViewHolder(view, arr, fr, onAddressSelected)
    }

    override fun onBindViewHolder(holder: BottomSheetChooseAddressViewHolder, position: Int) {
        val address = arr[position]
        holder.binding.tvAddress.text = address.tendiachi
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}
class BottomSheetChooseAddressViewHolder(
    v: View,
    var arr: ArrayList<Diachi>,
    var fr: BtsChooseAddressFragment,
    var onAddressSelected: OnAddressSelected
): RecyclerView.ViewHolder(v){
    val binding = AddressItemBinding.bind(v)
    init {
        binding.lnChooseAddress.setOnClickListener {
            onAddressSelected.onSelected(arr[adapterPosition])
            fr.dismiss()
        }
    }
}