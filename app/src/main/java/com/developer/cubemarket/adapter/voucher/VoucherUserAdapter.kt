package com.developer.cubemarket.adapter.voucher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.Util
import com.developer.cubemarket.R
import com.developer.cubemarket.call_back_view.OnVoucherSelected
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai
import com.developer.cubemarket.databinding.VoucherItemUserBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsVoicherFragment
import es.dmoral.toasty.Toasty

class VoucherUserAdapter(
    var fr: BtsVoicherFragment,
    var sumMoney: Int,
    var arr: ArrayList<KhuyenMai>,
    var onVoucherSelected: OnVoucherSelected
): RecyclerView.Adapter<VoucherUserAdapter.VoucherUserViewHolder>() {
    class VoucherUserViewHolder(
        v: View,
        var arr: ArrayList<KhuyenMai>,
        var onVoucherSelected: OnVoucherSelected,
        var fr: BtsVoicherFragment,
        var sumMoney: Int
    ): RecyclerView.ViewHolder(v){
        val binding = VoucherItemUserBinding.bind(v)
        init {
            binding.btnChoose.setOnClickListener {
                if(sumMoney >= arr[adapterPosition].sotiengiam){
                    onVoucherSelected.onSelected(arr[adapterPosition])
                    fr.dismiss()
                }else{
                    Toasty.warning(fr.requireContext(), "Mã này chỉ áp dụng với đơn hàng trên ${Utils.formaterVND(arr[adapterPosition].sotiengiam)}").show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.voucher_item_user, parent, false)
        return VoucherUserViewHolder(view, arr, onVoucherSelected, fr, sumMoney)
    }

    override fun onBindViewHolder(holder: VoucherUserViewHolder, position: Int) {
        val mg = arr[position]
        holder.binding.tvNameVoicher.text = mg.magiamgia
        holder.binding.tvPercentDown.text = "Giảm: ${mg.phantramgiam}% cho tất cả sản phẩm trên ${Utils.formaterVND(mg.sotiengiam)}"
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}