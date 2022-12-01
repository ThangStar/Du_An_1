package com.developer.cubemarket.adapter.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.connection.MODEL.OOP.User
import com.developer.cubemarket.databinding.UserManagerItemBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsChangePermissionUserFragment

class UserManagerAdapter(
    var fr: FragmentActivity,
    var arr: ArrayList<User>
): RecyclerView.Adapter<UserManagerAdapter.UserManagerViewHolder>() {
    class UserManagerViewHolder(
        v: View,
        private var fr: FragmentActivity,
        var arr: ArrayList<User>
    ): RecyclerView.ViewHolder(v), View.OnClickListener {
        val binding = UserManagerItemBinding.bind(v)
        init {
            binding.btnChangePermission.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when(p0!!){
                binding.btnChangePermission -> {
                    val bottomSheet = BtsChangePermissionUserFragment(arr[adapterPosition].id, arr[adapterPosition].chucvu)
                    bottomSheet.show(fr.supportFragmentManager, BtsChangePermissionUserFragment.TAG)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserManagerViewHolder {
        val bind = LayoutInflater.from(parent.context).inflate(R.layout.user_manager_item, parent, false)
        return UserManagerViewHolder(bind, fr, arr)
    }

    override fun onBindViewHolder(holder: UserManagerViewHolder, position: Int) {
        val us = arr[position]
        holder.binding.tvUsername.text = us.ten
        holder.binding.tvEmail.text = us.gmail

        var cv = "Không xác định!"
        when(us.chucvu){
            0 -> cv = "Người dùng"
            1 -> cv = "Người bán"
            2 -> cv = "Quản trị viên"
        }
        holder.binding.tvPermission.text = "Chức vụ: $cv"
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}