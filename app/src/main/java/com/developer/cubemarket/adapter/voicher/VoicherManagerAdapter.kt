package com.developer.cubemarket.adapter.voicher

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.call_back_view.CallBackNotifiDataUi
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai
import com.developer.cubemarket.databinding.VoicherItemBinding
import es.dmoral.toasty.Toasty

class VoicherManagerAdapter(
    var fr: Fragment,
    var arr: ArrayList<KhuyenMai>

):
    RecyclerView.Adapter<VoicherManagerAdapter.VoicherManagerViewHolder>() {
    class VoicherManagerViewHolder(
        v: View,
        var arr: ArrayList<KhuyenMai>,
        var fr: Fragment,
        var voicherManagerAdapter: VoicherManagerAdapter
    ): RecyclerView.ViewHolder(v){
        val binding = VoicherItemBinding.bind(v)
        init {
            binding.rlShowUtils.setOnLongClickListener{
                showMenu(fr.requireContext(), binding.rlShowUtils, R.menu.menu_utils)
                true
            }
        }
        fun showMenu(ctx: Context, v: View, @MenuRes menuRes: Int) {
            val popup = PopupMenu(ctx, v)
            popup.menuInflater.inflate(menuRes, popup.menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.it_del ->{
                        val callBackUi = object : CallBackNotifiDataUi{
                            override fun onSuccess() {
                                arr.removeAt(adapterPosition)
                                voicherManagerAdapter.notifyItemRemoved(adapterPosition)
                                Toasty.success(fr.requireContext(), "Xóa thành công!", Toasty.LENGTH_SHORT).show()
                            }
                        }
                        Utils.dialogDelVoicher(callBackUi, fr.requireActivity(), "Xóa mã giảm giá ${arr[adapterPosition].magiamgia}?",
                            "Bạn có muốn xóa mã giảm giá ${arr[adapterPosition].magiamgia}", arr[adapterPosition].magiamgia, adapterPosition)
                    }
                    R.id.it_edit ->{
                        val bundle = Bundle()
                        bundle.putString("CODE", arr[adapterPosition].magiamgia)
                        bundle.putInt("PERCENT", arr[adapterPosition].phantramgiam)
                        bundle.putInt("MIN_PRICE", arr[adapterPosition].sotiengiam)
                        bundle.putString("TIME_FIRST", arr[adapterPosition].ngaybatdau)
                        bundle.putString("TIME_END", arr[adapterPosition].ngayketthuc)
                        fr.findNavController().navigate(R.id.action_voicherManagerFragment_to_updateVoicherFragment,
                        bundle)

                    }
                }
                true
            }
            // Show the popup menu.
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoicherManagerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.voicher_item, parent, false)
        return VoicherManagerViewHolder(v, arr, fr, this)
    }

    override fun onBindViewHolder(holder: VoicherManagerViewHolder, position: Int) {
        val mg = arr[position]
        holder.binding.tvNameVoicher.text = mg.magiamgia
        holder.binding.tvDateStart.text = "Bắt đầu: ${mg.ngaybatdau}"
        holder.binding.tvDateEnd.text = "Kết thúc: ${mg.ngayketthuc}"
        holder.binding.tvPercentDown.text = "Giảm: ${mg.phantramgiam}%"
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}