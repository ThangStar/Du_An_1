package com.developer.cubemarket.adapter.checkout

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.connection.callback.CallBackDeleteCart
import com.developer.cubemarket.databinding.CheckoutProductItemBinding

class CheckOutCartAdapter(
    var fr: Fragment,
    var arr: ArrayList<GioHang>
): RecyclerView.Adapter<CheckOutCartAdapter.CheckOutCartViewHolder>() {
    @SuppressLint("SetTextI18n")
    class CheckOutCartViewHolder(
        v: View,
        var arr: ArrayList<GioHang>,
        var checkOutCartAdapter: CheckOutCartAdapter,
        var fr: Fragment
    ): RecyclerView.ViewHolder(v){
        val binding = CheckoutProductItemBinding.bind(v)

        init {
            binding.rlProductCheckOut.setOnClickListener{
                fr.findNavController().navigate(R.id.action_checkOutFragment_to_detailProductFragment)
            }
            binding.imvPlus.setOnClickListener {
                //plus tvAmount
                val tvAmount = binding.tvAmount.text.toString()
                var count = tvAmount.toInt()
                if(count < 9){
                    binding.tvAmount.text = "0${++count}"
                }else{
                    binding.tvAmount.text = "${++count}"
                }
            }
            binding.imvMinus.setOnClickListener {
                //minus tvAmount
                val tvAmount = binding.tvAmount.text.toString()
                var count = tvAmount.toInt()
                if(count > 1){
                    if (count < 9){
                        binding.tvAmount.text = "0${--count}"
                    }else{
                        binding.tvAmount.text = "${--count}"
                    }
                }else{
                    val callBackDeleteCart = object : CallBackDeleteCart{
                        override fun onSuccess() {
                            arr.removeAt(adapterPosition)
                            checkOutCartAdapter.notifyItemRemoved(adapterPosition)
                        }

                        override fun onFail() {
                        }

                        override fun onError() {
                        }

                    }
                    DaoGioHang(fr.requireContext()).delete_giohang(callBackDeleteCart, arr[adapterPosition].magiohang)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutCartViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.checkout_product_item, parent, false)
        return CheckOutCartViewHolder(v, arr, this, fr)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CheckOutCartViewHolder, position: Int) {
        val gh = arr[position]

        val option = Utils.getOptionLoadImgDirectoryFromUrl()
        Glide.with(fr.requireContext()).load(gh.img).apply(option).into(holder.binding.imvProduct);
        holder.binding.tvName.text = gh.tensanpham
        holder.binding.tvInfo.text = "Kích thước: ${gh.tenkichthuoc} màu: ${gh.tenmau}"
        holder.binding.tvPrice.text = Utils.formaterVND(gh.gia)
        holder.binding.tvAmount.text = "${gh.soluong}"

    }

    override fun getItemCount(): Int {

        return arr.size
    }

}